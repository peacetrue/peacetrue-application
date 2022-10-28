import * as React from 'react';
import {DateField, ReferenceField, Show, ShowProps, SimpleShowLayout, TextField} from 'react-admin';

export const DictionaryValueShow = (props: ShowProps) => {
  return (
    <Show {...props}>
      <SimpleShowLayout>
        <ReferenceField reference="dictionary-types" source="dictionaryTypeId" link="show">
          <TextField source="name"/>
        </ReferenceField>
        <TextField source="code"/>
        <TextField source="name"/>
        <TextField source="remark"/>
        <DateField source="createdTime" showTime/>
        <DateField source="modifiedTime" showTime/>
      </SimpleShowLayout>
    </Show>
  );
};
