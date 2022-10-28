import * as React from "react";
import {Resource} from "react-admin";

import {DictionaryValueList} from './List';
import {DictionaryValueCreate} from './Create';
import {DictionaryValueEdit} from './Edit';
import {DictionaryValueShow} from './Show';

export const DictionaryValue = {
  list: DictionaryValueList,
  create: DictionaryValueCreate,
  edit: DictionaryValueEdit,
  show: DictionaryValueShow
};
// react-jsx-dev-runtime.development.js:87 Warning: Each child in a list should have a unique "key" prop. Check the render method of `App`. See https://reactjs.org/link/warning-keys for more information.
export const DictionaryValueResource = <Resource key="dictionary-values" name="dictionary-values" {...DictionaryValue} />;
export default DictionaryValueResource;
export * from "./Messages"
