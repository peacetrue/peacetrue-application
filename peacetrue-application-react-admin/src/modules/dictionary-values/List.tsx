import * as React from 'react';
import {
  Datagrid,
  DateField,
  DateInput,
  EditButton,
  Filter,
  List,
  ListProps,
  ReferenceField,
  TextField,
  TextInput
} from 'react-admin';
import {DictionaryTypeSelect} from "./DictionaryTypeSelect";

const Filters = (props: any) => (
  <Filter {...props}>
    {DictionaryTypeSelect()}
    <TextInput source="code" alwaysOn resettable/>
    <TextInput source="name" alwaysOn resettable/>
    <DateInput source="createdTime.lowerBound" alwaysOn/>
    <DateInput source="createdTime.upperBound" alwaysOn/>
  </Filter>
);

export const DictionaryValueList = (props: ListProps) => {
  return (
    <List {...props} filters={<Filters/>} sort={undefined}>
      <Datagrid rowClick="show">
        <ReferenceField reference="dictionary-types" source="dictionaryTypeId" link="show">
          <TextField source="name"/>
        </ReferenceField>
        <TextField source="code"/>
        <TextField source="name"/>
        <TextField source="serialNumber"/>
        <DateField source="createdTime" showTime/>
        <DateField source="modifiedTime" showTime/>
        <EditButton/>
      </Datagrid>
    </List>
  )
};
