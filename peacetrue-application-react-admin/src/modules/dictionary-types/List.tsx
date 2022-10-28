import * as React from 'react';
import {Datagrid, DateField, DateInput, EditButton, Filter, List, ListProps, TextField, TextInput} from 'react-admin';

const Filters = (props: any) => (
  <Filter {...props}>
    <TextInput source="code" alwaysOn resettable/>
    <TextInput source="name" alwaysOn resettable/>
    <DateInput source="createdTime.lowerBound" alwaysOn/>
    <DateInput source="createdTime.upperBound" alwaysOn/>
  </Filter>
);

export const DictionaryTypeList = (props: ListProps) => {
  return (
    <List {...props} filters={<Filters/>}>
      <Datagrid rowClick="show">
        <TextField source="code"/>
        <TextField source="name"/>
        <TextField source="remark"/>
        <DateField source="createdTime" showTime/>
        <DateField source="modifiedTime" showTime/>
        <EditButton/>
      </Datagrid>
    </List>
  )
};
