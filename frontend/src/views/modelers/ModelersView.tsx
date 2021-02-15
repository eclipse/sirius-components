/*******************************************************************************
 * Copyright (c) 2021 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
import { useQuery } from '@apollo/client';
import Button from '@material-ui/core/Button';
import Grid from '@material-ui/core/Grid';
import IconButton from '@material-ui/core/IconButton';
import Link from '@material-ui/core/Link';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import Paper from '@material-ui/core/Paper';
import Snackbar from '@material-ui/core/Snackbar';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Tooltip from '@material-ui/core/Tooltip';
import Typography from '@material-ui/core/Typography';
import CloseIcon from '@material-ui/icons/Close';
import EditIcon from '@material-ui/icons/Edit';
import MoreHorizIcon from '@material-ui/icons/MoreHoriz';
import PublishIcon from '@material-ui/icons/Publish';
import { useMachine } from '@xstate/react';
import gql from 'graphql-tag';
import { PublishModelerModal } from 'modals/publish-modeler/PublishModelerModal';
import { RenameModelerModal } from 'modals/rename-modeler/RenameModelerModal';
import React, { useEffect } from 'react';
import { Link as RouterLink, useParams } from 'react-router-dom';
import { Modeler } from 'views/modelers/ModelersView.types';
import { View } from 'views/View';
import {
  CloseMenuEvent,
  CloseModalEvent,
  FetchedModelersEvent,
  HideToastEvent,
  ModelersViewContext,
  ModelersViewEvent,
  modelersViewMachine,
  OpenMenuEvent,
  OpenModalEvent,
  SchemaValue,
  ShowToastEvent,
} from './ModelersViewMachine';

const getModelersQuery = gql`
  query getModelers($projectId: ID!) {
    viewer {
      project(projectId: $projectId) {
        modelers {
          id
          name
          status
        }
      }
    }
  }
`;

const useModelersViewStyles = makeStyles((theme) => ({
  modelersViewContainer: {
    display: 'flex',
    flexDirection: 'column',
  },
  header: {
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: theme.spacing(5),
  },
  actions: {
    display: 'flex',
    flexDirection: 'row',
  },
}));

export const ModelersView = () => {
  const classes = useModelersViewStyles();
  const { projectId } = useParams();
  const [{ value, context }, dispatch] = useMachine<ModelersViewContext, ModelersViewEvent>(modelersViewMachine);
  const { toast, modelersView } = value as SchemaValue;
  const { modelers, selectedModeler, menuAnchor, modalToDisplay, message } = context;

  const { loading, data, error, refetch } = useQuery(getModelersQuery, {
    variables: { projectId },
  });
  useEffect(() => {
    if (!loading) {
      if (error) {
        const showToastEvent: ShowToastEvent = {
          type: 'SHOW_TOAST',
          message: 'An unexpected error has occurred, please refresh the page',
        };
        dispatch(showToastEvent);
      }
      if (data) {
        const fetchModelersEvent: FetchedModelersEvent = { type: 'HANDLE_FETCHED_MODELERS', data };
        dispatch(fetchModelersEvent);
      }
    }
  }, [loading, data, error, dispatch]);

  const onMore = (event: React.MouseEvent<HTMLButtonElement, MouseEvent>, modeler: Modeler) =>
    dispatch({ type: 'OPEN_MENU', menuAnchor: event.currentTarget, modeler } as OpenMenuEvent);
  const onClose = () => dispatch({ type: 'CLOSE_MENU' } as CloseMenuEvent);
  const onRename = () => dispatch({ type: 'OPEN_MODAL', modalToDisplay: 'Rename' } as OpenModalEvent);
  const onPublish = () => dispatch({ type: 'OPEN_MODAL', modalToDisplay: 'Publish' } as OpenModalEvent);
  const onCloseModal = () => dispatch({ type: 'CLOSE_MODAL' } as CloseModalEvent);
  const refreshModelers = () => {
    dispatch({ type: 'CLOSE_MODAL' } as CloseModalEvent);
    refetch();
  };

  let main = null;
  if (modelersView === 'loaded') {
    let contextMenu = null;
    let modal = null;

    if (selectedModeler) {
      if (menuAnchor) {
        contextMenu = (
          <ModelerContextMenu
            menuAnchor={menuAnchor}
            modeler={selectedModeler}
            onRename={onRename}
            onPublish={onPublish}
            onClose={onClose}
          />
        );
      }
      if (modalToDisplay === 'Rename') {
        modal = (
          <RenameModelerModal
            modelerId={selectedModeler.id}
            initialModelerName={selectedModeler.name}
            onRename={refreshModelers}
            onClose={onCloseModal}
          />
        );
      } else if (modalToDisplay === 'Publish') {
        modal = (
          <PublishModelerModal modelerId={selectedModeler.id} onPublish={refreshModelers} onClose={onCloseModal} />
        );
      }
    }
    main = (
      <>
        <ModelersTable projectId={projectId} modelers={modelers} onMore={onMore} />
        {contextMenu}
        {modal}
      </>
    );
  }
  if (modelersView === 'empty') {
    main = <Message content="No modelers available, start by creating one" />;
  } else if (modelersView === 'missing') {
    main = <Message content="The project does not exist" />;
  }

  return (
    <View>
      <Grid container justify="center">
        <Grid item xs={8}>
          <div className={classes.modelersViewContainer}>
            <div className={classes.header}>
              <Typography variant="h3">Modelers</Typography>
              <div className={classes.actions}>
                <Button
                  to={`/projects/${projectId}/new/modeler`}
                  component={RouterLink}
                  data-testid="create"
                  color="primary"
                  variant="contained"
                  disabled={modelersView === 'missing'}>
                  New
                </Button>
              </div>
            </div>
            {main}
          </div>
        </Grid>
      </Grid>
      <Snackbar
        anchorOrigin={{
          vertical: 'bottom',
          horizontal: 'right',
        }}
        open={toast === 'visible'}
        autoHideDuration={3000}
        onClose={() => dispatch({ type: 'HIDE_TOAST' } as HideToastEvent)}
        message={message}
        action={
          <IconButton
            size="small"
            aria-label="close"
            color="inherit"
            onClick={() => dispatch({ type: 'HIDE_TOAST' } as HideToastEvent)}>
            <CloseIcon fontSize="small" />
          </IconButton>
        }
        data-testid="error"
      />
    </View>
  );
};

const Message = ({ content }) => {
  return (
    <Grid container justify="center">
      <Grid item xs={6}>
        <Typography variant="h4" align="center" gutterBottom>
          {content}
        </Typography>
      </Grid>
    </Grid>
  );
};

const ModelersTable = ({ projectId, modelers, onMore }) => {
  return (
    <Paper>
      <TableContainer>
        <Table>
          <colgroup>
            <col width="60%" />
            <col width="30%" />
            <col width="10%" />
          </colgroup>
          <TableHead>
            <TableRow>
              <TableCell variant="head">Name</TableCell>
              <TableCell variant="head">Status</TableCell>
              <TableCell variant="head"></TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {modelers.map((modeler) => (
              <TableRow key={modeler.id}>
                <TableCell>
                  <Link component={RouterLink} to={`/projects/${projectId}/edit`} color="inherit">
                    {modeler.name}
                  </Link>
                </TableCell>
                <TableCell>
                  <Typography>{modeler.status}</Typography>
                </TableCell>
                <TableCell align="right">
                  <Tooltip title="More">
                    <IconButton
                      aria-label="more"
                      onClick={(event) => onMore(event, modeler)}
                      size="small"
                      data-testid="more">
                      <MoreHorizIcon fontSize="small" />
                    </IconButton>
                  </Tooltip>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Paper>
  );
};

const ModelerContextMenu = ({ menuAnchor, onClose, onRename, onPublish, modeler }) => {
  return (
    <Menu
      data-testid="modeler-actions-contextmenu"
      id="modeler-actions-contextmenu"
      anchorEl={menuAnchor}
      keepMounted
      open={true}
      onClose={onClose}>
      <MenuItem onClick={onRename} data-testid="rename">
        <ListItemIcon>
          <EditIcon />
        </ListItemIcon>
        <ListItemText primary="Rename" />
      </MenuItem>
      <MenuItem disabled={modeler.status === 'PUBLISHED'} onClick={onPublish} data-testid="publish">
        <ListItemIcon>
          <PublishIcon />
        </ListItemIcon>
        <ListItemText primary="Publish" />
      </MenuItem>
    </Menu>
  );
};
