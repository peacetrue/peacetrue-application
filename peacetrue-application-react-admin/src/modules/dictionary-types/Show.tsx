import * as React from 'react';
import {DateField, Show, ShowProps, SimpleShowLayout, TextField} from 'react-admin';

export const DictionaryTypeShow = (props: ShowProps) => {
  return (
    <Show {...props}>
      <SimpleShowLayout>
        <TextField source="code"/>
        <TextField source="name"/>
        <TextField source="remark"/>
        <DateField source="createdTime" showTime/>
        <DateField source="modifiedTime" showTime/>
      </SimpleShowLayout>
    </Show>
  );
};
