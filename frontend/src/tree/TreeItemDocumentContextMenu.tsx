/*******************************************************************************
 * Copyright (c) 2019, 2021 Obeo.
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
import { makeStyles } from '@material-ui/core';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import AddIcon from '@material-ui/icons/Add';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import GetAppIcon from '@material-ui/icons/GetApp';
import { httpOrigin } from 'common/URL';
import React from 'react';
import { TreeItemDocumentContextMenuProps } from './TreeItemDocumentContextMenu.types';

const useTreeItemDocumentContextMenuStyles = makeStyles((theme) => ({
  item: {
    paddingTop: theme.spacing(0),
    paddingBottom: theme.spacing(0),
  },
}));

export const TreeItemDocumentContextMenu = ({
  editingContextId,
  documentId,
  onNewObject,
  onRenameDocument,
  onDownload,
  onDeleteDocument,
  onClose,
  readOnly,
  menuAnchor,
}: TreeItemDocumentContextMenuProps) => {
  const classes = useTreeItemDocumentContextMenuStyles();

  return (
    <Menu
      id="treeitemdocument-contextmenu"
      anchorEl={menuAnchor}
      keepMounted
      open
      onClose={onClose}
      data-testid="treeitemdocument-contextmenu"
      getContentAnchorEl={null}
      anchorOrigin={{
        vertical: 'bottom',
        horizontal: 'right',
      }}>
      <MenuItem onClick={onNewObject} data-testid="new-object" disabled={readOnly} dense className={classes.item}>
        <ListItemIcon>
          <AddIcon fontSize="small" />
        </ListItemIcon>
        <ListItemText primary="New object" />
      </MenuItem>
      <MenuItem onClick={onRenameDocument} data-testid="rename" disabled={readOnly} dense className={classes.item}>
        <ListItemIcon>
          <EditIcon fontSize="small" />
        </ListItemIcon>
        <ListItemText primary="Rename" />
      </MenuItem>
      <MenuItem
        divider
        onClick={onDownload}
        component="a"
        href={`${httpOrigin}/api/editingcontexts/${editingContextId}/documents/${documentId}`}
        type="application/octet-stream"
        data-testid="download"
        disabled={readOnly}
        dense
        className={classes.item}>
        <ListItemIcon>
          <GetAppIcon fontSize="small" />
        </ListItemIcon>
        <ListItemText primary="Download" />
      </MenuItem>
      <MenuItem onClick={onDeleteDocument} data-testid="delete" disabled={readOnly} dense className={classes.item}>
        <ListItemIcon>
          <DeleteIcon fontSize="small" />
        </ListItemIcon>
        <ListItemText primary="Delete" />
      </MenuItem>
    </Menu>
  );
};
