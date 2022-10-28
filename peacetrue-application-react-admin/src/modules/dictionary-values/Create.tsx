import * as React from 'react';
import {Create, CreateProps, maxLength, required, SimpleForm, TextInput} from 'react-admin';
import {DictionaryTypeSelect} from "./DictionaryTypeSelect";

export const DictionaryValueCreate = (props: CreateProps) => {
  return (
    <Create {...props}>
      <SimpleForm>
        {DictionaryTypeSelect()}
        <TextInput source="code" validate={[required(), maxLength(32)]}/>
        <TextInput source="name" validate={[required(), maxLength(255)]}/>
        <TextInput source="remark" validate={[maxLength(255)]} fullWidth multiline/>
        <TextInput source="serialNumber" validate={[required(), maxLength(32767)]}/>
      </SimpleForm>
    </Create>
  );
};
