package com.allitov.webcontactsmanager.model.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class Contact {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;
}
