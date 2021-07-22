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
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import React from 'react';
import { TreeItemDiagramContextMenuProps } from './TreeItemDiagramContextMenu.types';

const useTreeItemDiagramContextMenuStyles = makeStyles((theme) => ({
  item: {
    paddingTop: theme.spacing(1),
    paddingBottom: theme.spacing(1),
  },
}));

export const TreeItemDiagramContextMenu = ({
  onDeleteRepresentation,
  onRenameRepresentation,
  onClose,
  readOnly,
  menuAnchor,
}: TreeItemDiagramContextMenuProps) => {
  const classes = useTreeItemDiagramContextMenuStyles();

  return (
    <Menu
      id="treeitemdiagram-contextmenu"
      anchorEl={menuAnchor}
      keepMounted
      open
      onClose={onClose}
      data-testid="treeitemdiagram-contextmenu"
      getContentAnchorEl={null}
      anchorOrigin={{
        vertical: 'bottom',
        horizontal: 'right',
      }}>
      <MenuItem
        divider
        onClick={onRenameRepresentation}
        data-testid="rename-representation"
        disabled={readOnly}
        dense
        className={classes.item}>
        <ListItemIcon>
          <EditIcon fontSize="small" />
        </ListItemIcon>
        <ListItemText primary="Rename" />
      </MenuItem>
      <MenuItem
        onClick={onDeleteRepresentation}
        data-testid="delete-representation"
        disabled={readOnly}
        dense
        className={classes.item}>
        <ListItemIcon>
          <DeleteIcon fontSize="small" />
        </ListItemIcon>
        <ListItemText primary="Delete" />
      </MenuItem>
    </Menu>
  );
};
