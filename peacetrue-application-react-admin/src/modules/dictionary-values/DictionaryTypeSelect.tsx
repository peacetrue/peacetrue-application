import * as React from "react";
import {AutocompleteInput, ReferenceInput} from "react-admin";

export function DictionaryTypeSelect() {
  return (
    <ReferenceInput reference="dictionary-types" source="dictionaryTypeId"
                    sort={{field: 'name', order: 'ASC'}}
                    alwaysOn>
      <AutocompleteInput optionText={"name"}
                         filterToQuery={key => ({name: key})}
                         helperText={"使用名称搜索，按名称字典序排列"}
      />
    </ReferenceInput>
  );
}
