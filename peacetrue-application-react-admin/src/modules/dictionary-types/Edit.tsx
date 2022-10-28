import * as React from 'react';
import {DateTimeInput, Edit, EditProps, maxLength, required, SimpleForm, TextInput} from 'react-admin';

export const DictionaryTypeEdit = (props: EditProps) => {
  return (
    <Edit {...props}>
      <SimpleForm>
        <TextInput source="code" disabled/>
        <TextInput source="name" validate={[required(), maxLength(32)]}/>
        <TextInput source="remark" validate={[maxLength(255)]} fullWidth multiline/>
        <DateTimeInput source="createdTime" disabled/>
        <DateTimeInput source="modifiedTime" disabled/>
      </SimpleForm>
    </Edit>
  );
};
