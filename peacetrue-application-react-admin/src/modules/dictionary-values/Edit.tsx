import * as React from 'react';
import {
  Edit,
  EditProps,
  maxLength,
  NumberInput,
  ReferenceInput,
  required,
  SelectInput,
  SimpleForm,
  TextInput
} from 'react-admin';

export const DictionaryValueEdit = (props: EditProps) => {
  return (
    <Edit {...props}>
      <SimpleForm>
        <ReferenceInput reference="dictionary-types" source="dictionaryTypeId" link="show">
          <SelectInput optionText="name" disabled/>
        </ReferenceInput>
        <TextInput source="code" disabled/>
        <TextInput source="name" validate={[required(), maxLength(32)]}/>
        <NumberInput source="serialNumber" validate={[required()]}/>
        <TextInput source="remark" validate={[maxLength(255)]} fullWidth multiline/>
      </SimpleForm>
    </Edit>
  );
};
