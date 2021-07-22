/*******************************************************************************
 * Copyright (c) 2019, 2020 Obeo.
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
import React from 'react';
import { TreeItemObjectContextMenuProps } from './TreeItemObjectContextMenu.types';

const useTreeItemObjectContextMenuStyles = makeStyles((theme) => ({
  item: {
    paddingTop: theme.spacing(1),
    paddingBottom: theme.spacing(1),
  },
}));

export const TreeItemObjectContextMenu = ({
  onCreateNewObject,
  onCreateRepresentation,
  onRenameObject,
  editable,
  onDeleteObject,
  onClose,
  readOnly,
  menuAnchor,
}: TreeItemObjectContextMenuProps) => {
  const classes = useTreeItemObjectContextMenuStyles();

  return (
    <Menu
      id="treeitemobject-contextmenu"
      anchorEl={menuAnchor}
      keepMounted
      open
      onClose={onClose}
      data-testid="treeitemobject-contextmenu"
      getContentAnchorEl={null}
      anchorOrigin={{
        vertical: 'bottom',
        horizontal: 'right',
      }}>
      <MenuItem onClick={onCreateNewObject} data-testid="new-object" disabled={readOnly} dense className={classes.item}>
        <ListItemIcon>
          <AddIcon fontSize="small" />
        </ListItemIcon>
        <ListItemText primary="New object" />
      </MenuItem>
      <MenuItem
        divider
        onClick={onCreateRepresentation}
        data-testid="new-representation"
        disabled={readOnly}
        dense
        className={classes.item}>
        <ListItemIcon>
          <AddIcon fontSize="small" />
        </ListItemIcon>
        <ListItemText primary="New representation" />
      </MenuItem>
      <MenuItem
        divider
        onClick={onRenameObject}
        data-testid="rename-object"
        disabled={readOnly || !editable}
        dense
        className={classes.item}>
        <ListItemIcon>
          <EditIcon fontSize="small" />
        </ListItemIcon>
        <ListItemText primary="Rename" />
      </MenuItem>
      <MenuItem onClick={onDeleteObject} data-testid="delete-object" disabled={readOnly} dense className={classes.item}>
        <ListItemIcon>
          <DeleteIcon fontSize="small" />
        </ListItemIcon>
        <ListItemText primary="Delete" />
      </MenuItem>
    </Menu>
  );
};
