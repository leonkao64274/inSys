INSERT INTO t_admin_user (oid, create_date, create_time, update_date, update_time, account, department, email, ext, last_login_dttm, last_pswd_dttm, op_type, password, tel, user_name, user_type, admin_group_oid) VALUES ('1', '  0701', '210538', '  0907', '154611', 'SYSADMIN', 'IT Department', NULL, NULL, '  0907154550', '  0907143235', '1', '1bbb81e8a4b9556778262618c935a884b1844e91', NULL, 'Administrator', '0', NULL);
INSERT INTO t_admin_group (oid, create_date, create_time, update_date, update_time, description, group_id, group_name) VALUES ('1', '  0701', '231411', '  0701', '231411', 'op', 'op', 'op');

INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('1', 'admin_group', '1');
INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('2', 'sign_cert_query', '1');
INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('3', 'operation_log', '1');
INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('4', 'cek', '1');
INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('5', 'admin_group_query', '1');
INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('6', 'ds_cert_management', '1');
INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('7', 'kek', '1');
INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('8', 'sign_csr', '1');
INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('9', 'admin_user_query', '1');
INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('10', 'trans_argument', '1');
INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('11', 'trans_argument', '1');
INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('12', 'directory_server', '1');
INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('13', 'ca_cert_query', '1');
INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('14', 'ca_cert_load', '1');
INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('15', 'ds_cardrange', '1');
INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('16', 'admin_user', '1');
INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('17', 'sign_cert_activate', '1');
INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('18', 'trans_query', '1');
INSERT INTO t_admin_group_privilege (oid, access_id, admin_group_oid) VALUES ('19', 'sign_cert_load', '1');


INSERT INTO t_admin_user (oid, create_date, create_time, update_date, update_time, account, department, email, ext, last_login_dttm, last_pswd_dttm, op_type, password, tel, user_name, user_type, admin_group_oid) VALUES ('2', '  0712', '090206', '  0809', '173619', 'Bob', 'RD', NULL, NULL, '  0809173619', '  0712090206', '0', '9035ea3f0f857d0209ad86046d7119a999dbd63d', NULL, 'Bob', '1', '1');


INSERT INTO t_invSys_trans (oid, create_date, create_time, update_date, update_time, acct_id, acct_number, acct_number_postfix, acct_number_prefix, acct_type, acquirer_bin, acquirer_merchant_id, acs_operator_id, acs_reference_number, acs_signed_content, acs_trans_id, acs_url, acs_ui_interface, acs_ui_type, acs_challenge_mandated, addr_match, authentication_method, authentication_type, authentication_value, bill_addr_city, bill_addr_country, bill_addr_line1, bill_addr_line2, bill_addr_line3, bill_addr_state, browser_accept_header, browser_color_depth, browser_ip, browser_java_enabled, browser_language, browser_screen_height, browser_screen_width, browser_tz, browser_user_agent, card_expiry_date, card_scheme, cardholder_info, cardholder_name, cek_alias, challenge_cancel, device_channel, device_ui_interface, device_ui_type, ds_reference_number, ds_trans_id, eci, email, error_code, error_component, error_description, error_detail, error_message_type, interaction_counter, mcc, merchant_country_code, merchant_name, message_category, message_version, notification_url, pay_token_ind, purchase_amount, purchase_currency, purchase_date, purchase_exponent, purchase_instal_data, recurring_expiry, recurring_frequency, result_status, sdk_app_id, sdk_max_timeout, sdk_reference_number, sdk_trans_id, ship_addr_city, ship_addr_country, ship_addr_line1, ship_addr_line2, ship_addr_line3, ship_addr_post_code, ship_addr_state, testcase_id, invSys_comp_ind, invSys_requestor_3ri_ind, invSys_requestor_chlg_ind, invSys_requestor_id, invSys_requestor_npa_ind, invSys_requestor_name, invSys_requestor_url, invSys_server_operator_id, invSys_server_ref_number, invSys_server_trans_id, invSys_server_url, invSys_session_data, trans_status, trans_status_reason, trans_type) VALUES ('1', '  0905', '101629', '  0905', '101629', 'A987654321', 'U+aBgawJU8P+H4kwE2ElFsr9CegzVxQl', '0123', '515352', '02', '1010225588', '45045077123', NULL, 'invSys_LOA_ACS_PPFU_020100_00009', '123', '92e9734f-f923-4229-a251-5dc52991a720', NULL, '01', '02', 'Y', 'Y', '02', '02', 'U+e5qQAAAAAAAAAAAAAAAAAAAAA=', 'Taipei', '156', 'Taipei, Taiwan', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2512', 'M', 'null', 'CTBC_TEST', 'CEK_  0701210531', NULL, '01', NULL, NULL, 'DsReferenceNumber12837129312', 'F896AF97-303B-4B24-BDA7-26DC67E0D92D', '01', 'kevinchen@esobi.com', NULL, NULL, NULL, NULL, NULL, '02', '5450', '156', 'QQAZZ', '01', '2.1.0', NULL, 'true', '1019600', '901', '  0905021624', '2', NULL, ' 0616', '3', '01', '90610ae7-57b3-4233-9a3d-96dd097b3b61', NULL, 'invSys_LOA_SDK_PPFU_020100_00011', '5d4857dc-670e-4470-a675-162e516d9803', 'Taipei', '156', 'Taipei, Taiwan', NULL, NULL, '710', NULL, NULL, NULL, NULL, NULL, '05060012477', NULL, 'pass', 'http://google.com/', 'invSysServerOperatorUL', 'invSys_LOA_SER_PPFU_020100_00008', '38f3ecbb-3611-48e6-91a0-fcab21d3c89a', 'http://demo.higotw.com.tw:30200/invSysserver-auth/challenge/2.1.0/123/rreq', NULL, 'Y', NULL, '01');
INSERT INTO t_invSys_trans (oid, create_date, create_time, update_date, update_time, acct_id, acct_number, acct_number_postfix, acct_number_prefix, acct_type, acquirer_bin, acquirer_merchant_id, acs_operator_id, acs_reference_number, acs_signed_content, acs_trans_id, acs_url, acs_ui_interface, acs_ui_type, acs_challenge_mandated, addr_match, authentication_method, authentication_type, authentication_value, bill_addr_city, bill_addr_country, bill_addr_line1, bill_addr_line2, bill_addr_line3, bill_addr_state, browser_accept_header, browser_color_depth, browser_ip, browser_java_enabled, browser_language, browser_screen_height, browser_screen_width, browser_tz, browser_user_agent, card_expiry_date, card_scheme, cardholder_info, cardholder_name, cek_alias, challenge_cancel, device_channel, device_ui_interface, device_ui_type, ds_reference_number, ds_trans_id, eci, email, error_code, error_component, error_description, error_detail, error_message_type, interaction_counter, mcc, merchant_country_code, merchant_name, message_category, message_version, notification_url, pay_token_ind, purchase_amount, purchase_currency, purchase_date, purchase_exponent, purchase_instal_data, recurring_expiry, recurring_frequency, result_status, sdk_app_id, sdk_max_timeout, sdk_reference_number, sdk_trans_id, ship_addr_city, ship_addr_country, ship_addr_line1, ship_addr_line2, ship_addr_line3, ship_addr_post_code, ship_addr_state, testcase_id, invSys_comp_ind, invSys_requestor_3ri_ind, invSys_requestor_chlg_ind, invSys_requestor_id, invSys_requestor_npa_ind, invSys_requestor_name, invSys_requestor_url, invSys_server_operator_id, invSys_server_ref_number, invSys_server_trans_id, invSys_server_url, invSys_session_data, trans_status, trans_status_reason, trans_type) VALUES ('2', '  0905', '104556', '  0905', '104556', 'A987654321', 'jDSXIvIESSj+H4kwE2ElFsr9CegzVxQl', '0123', '625352', '02', '1010225588', '45045077123', NULL, 'invSys_LOA_ACS_PPFU_020100_00009', '456', 'cacf64f7-935a-4dd1-823f-d77c4f5ce5d8', NULL, '01', '02', 'Y', 'Y', '02', '02', 'BQEUVgIAJVEFiBCGg4AlJCcpAAA=', 'Taipei', '156', 'Taipei, Taiwan', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2512', 'C', 'null', 'CTBC_TEST', 'CEK_  0701210531', NULL, '01', NULL, NULL, 'DsReferenceNumber12837129312', '4FD793C4-776A-4EC2-B1A6-C4C688E6B445', '05', 'kevinchen@esobi.com', NULL, NULL, NULL, NULL, NULL, '01', '5450', '156', 'QQAZZ', '01', '2.1.0', NULL, 'true', '1019600', '901', '  0905104554', '2', NULL, ' 0616', '3', '01', '90610ae7-57b3-4233-9a3d-96dd097b3b61', NULL, 'invSys_LOA_SDK_PPFU_020100_00011', '0400a9ac-c9e2-47d0-9698-6376f4e04bdd', 'Taipei', '156', 'Taipei, Taiwan', NULL, NULL, '710', NULL, NULL, NULL, NULL, NULL, '05060012477', NULL, 'pass', 'http://google.com/', 'invSysServerOperatorUL', 'invSys_LOA_SER_PPFU_020100_00008', 'b4974679-9502-4cfd-9092-860c8a32b7c8', 'http://demo.higotw.com.tw:30200/invSysserver-auth/challenge/2.1.0/123/rreq', NULL, 'Y', NULL, '01');
INSERT INTO t_invSys_trans (oid, create_date, create_time, update_date, update_time, acct_id, acct_number, acct_number_postfix, acct_number_prefix, acct_type, acquirer_bin, acquirer_merchant_id, acs_operator_id, acs_reference_number, acs_signed_content, acs_trans_id, acs_url, acs_ui_interface, acs_ui_type, acs_challenge_mandated, addr_match, authentication_method, authentication_type, authentication_value, bill_addr_city, bill_addr_country, bill_addr_line1, bill_addr_line2, bill_addr_line3, bill_addr_state, browser_accept_header, browser_color_depth, browser_ip, browser_java_enabled, browser_language, browser_screen_height, browser_screen_width, browser_tz, browser_user_agent, card_expiry_date, card_scheme, cardholder_info, cardholder_name, cek_alias, challenge_cancel, device_channel, device_ui_interface, device_ui_type, ds_reference_number, ds_trans_id, eci, email, error_code, error_component, error_description, error_detail, error_message_type, interaction_counter, mcc, merchant_country_code, merchant_name, message_category, message_version, notification_url, pay_token_ind, purchase_amount, purchase_currency, purchase_date, purchase_exponent, purchase_instal_data, recurring_expiry, recurring_frequency, result_status, sdk_app_id, sdk_max_timeout, sdk_reference_number, sdk_trans_id, ship_addr_city, ship_addr_country, ship_addr_line1, ship_addr_line2, ship_addr_line3, ship_addr_post_code, ship_addr_state, testcase_id, invSys_comp_ind, invSys_requestor_3ri_ind, invSys_requestor_chlg_ind, invSys_requestor_id, invSys_requestor_npa_ind, invSys_requestor_name, invSys_requestor_url, invSys_server_operator_id, invSys_server_ref_number, invSys_server_trans_id, invSys_server_url, invSys_session_data, trans_status, trans_status_reason, trans_type) VALUES ('3', '  0905', '110311', '  0905', '110311', 'A987654321', 'jDSXIvIESSj+H4kwE2ElFsr9CegzVxQl', '0123', '625352', '02', '1010225588', '45045077123', NULL, 'invSys_LOA_ACS_PPFU_020100_00009', '789', '13de6e3a-77a1-479e-aad1-e50f4d389619', NULL, '01', '02', 'Y', 'Y', '02', '02', 'BQEUVgIAJgR0GSEZIJaCh1kJAAA=', 'Taipei', '156', 'Taipei, Taiwan', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2512', 'C', 'null', 'CTBC_TEST', 'CEK_  0701210531', NULL, '01', NULL, NULL, 'DsReferenceNumber12837129312', 'B0958E9F-7224-4CA4-A8D9-638DBDF8DDC5', '05', 'kevinchen@esobi.com', NULL, NULL, NULL, NULL, NULL, '02', '5450', '156', 'QQAZZ', '01', '2.1.0', NULL, 'true', '1019600', '901', '  0905110310', '2', NULL, ' 0616', '3', '01', '90610ae7-57b3-4233-9a3d-96dd097b3b61', NULL, 'invSys_LOA_SDK_PPFU_020100_00011', 'bd79b90c-bae5-4638-b680-d2db8187b515', 'Taipei', '156', 'Taipei, Taiwan', NULL, NULL, '710', NULL, NULL, NULL, NULL, NULL, '05060012477', NULL, 'pass', 'http://google.com/', 'invSysServerOperatorUL', 'invSys_LOA_SER_PPFU_020100_00008', '0e4c44f0-cd02-4cde-8d1a-c77772f3468d', 'http://demo.higotw.com.tw:30200/invSysserver-auth/challenge/2.1.0/123/rreq', NULL, 'Y', NULL, '01');

INSERT INTO t_operation_log (oid, create_date, create_time, update_date, update_time, access_id, account, action, data_after, data_before, data_query, i18n_code, ip_addr, result, target_object, user_name) VALUES ('1', '  0703', '020400', '  0703', '020400', 'kek', 'alex', 'Q', NULL, NULL, '{\"criteriaKeyAlias\":null,\"criteriaStatus\":null,\"pageNumber\":0}', 'ui.kek', '192.168.100.187', '0', 'T_KMS_INFO', 'alex');
INSERT INTO t_operation_log (oid, create_date, create_time, update_date, update_time, access_id, account, action, data_after, data_before, data_query, i18n_code, ip_addr, result, target_object, user_name) VALUES ('2', '  0921', '093850', '  0921', '093850', 'directory_server', 'alex', 'E', '{\"oid\":1,\"createDate\":null,\"createTime\":null,\"updateDate\":null,\"updateTime\":null,\"cardScheme\":\"V\",\"areqUrl\":\"http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/2/123/areq\",\"backupAreqUrl\":\"http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/2/123/areq\",\"preqUrl\":\"http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/2/123/areq\",\"backupPreqUrl\":\"http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/2/123/areq\",\"messageVersion\":\"2.1.0\",\"retryLimits\":3,\"retryInterval\":5,\"readTimeout\":30,\"serialNum\":null}', '{\"oid\":1,\"createDate\":\"  0702\",\"createTime\":\"234136\",\"updateDate\":\"  0919\",\"updateTime\":\"102627\",\"cardScheme\":\"V\",\"areqUrl\":\"http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/2/123/areq\",\"backupAreqUrl\":\"http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/2/123/areq\",\"preqUrl\":\"http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/2/123/areq\",\"backupPreqUrl\":\"http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/2/123/areq\",\"messageVersion\":\"2.1.0\",\"retryLimits\":3,\"retryInterval\":5,\"readTimeout\":30,\"serialNum\":null}', NULL, 'ui.directory-server', '192.168.120.40', '0', 'T_DIRECTORY_SERVER', 'alex');
INSERT INTO t_operation_log (oid, create_date, create_time, update_date, update_time, access_id, account, action, data_after, data_before, data_query, i18n_code, ip_addr, result, target_object, user_name) VALUES ('3', '  0920', '094104', '  0920', '094104', 'admin_group', 'Bob', 'E', '{\"oid\":3,\"createDate\":null,\"createTime\":null,\"updateDate\":null,\"updateTime\":null,\"groupId\":\"t1\",\"groupName\":\"t1\",\"description\":\"t1\",\"adminGroupPrivileges\":[{\"oid\":null,\"accessId\":\"trans_query\"},{\"oid\":null,\"accessId\":\"sign_cert_query\"},{\"oid\":null,\"accessId\":\"sign_csr\"},{\"oid\":null,\"accessId\":\"sign_cert_activate\"},{\"oid\":null,\"accessId\":\"admin_user\"},{\"oid\":null,\"accessId\":\"kek\"},{\"oid\":null,\"accessId\":\"directory_server\"},{\"oid\":null,\"accessId\":\"trans_argument\"},{\"oid\":null,\"accessId\":\"admin_group_query\"},{\"oid\":null,\"accessId\":\"admin_user_query\"},{\"oid\":null,\"accessId\":\"ca_cert_query\"},{\"oid\":null,\"accessId\":\"admin_group\"},{\"oid\":null,\"accessId\":\"ds_cert_management\"},{\"oid\":null,\"accessId\":\"operation_log\"},{\"oid\":null,\"accessId\":\"ca_cert_load\"},{\"oid\":null,\"accessId\":\"ds_cardrange\"},{\"oid\":null,\"accessId\":\"cek\"},{\"oid\":null,\"accessId\":\"sign_cert_load\"}]}', '{\"oid\":3,\"createDate\":\"  0830\",\"createTime\":\"121647\",\"updateDate\":\"  0830\",\"updateTime\":\"134207\",\"groupId\":\"t1\",\"groupName\":\"t1\",\"description\":\"t1\",\"adminGroupPrivileges\":[{\"oid\":100,\"accessId\":\"sign_cert_load\"},{\"oid\":103,\"accessId\":\"directory_server\"},{\"oid\":94,\"accessId\":\"operation_log\"},{\"oid\":95,\"accessId\":\"sign_cert_query\"},{\"oid\":107,\"accessId\":\"ds_cert_management\"},{\"oid\":92,\"accessId\":\"sign_cert_activate\"},{\"oid\":105,\"accessId\":\"trans_query\"},{\"oid\":101,\"accessId\":\"ca_cert_query\"},{\"oid\":97,\"accessId\":\"cek\"},{\"oid\":99,\"accessId\":\"admin_group\"},{\"oid\":96,\"accessId\":\"ds_cardrange\"},{\"oid\":106,\"accessId\":\"sign_csr\"},{\"oid\":104,\"accessId\":\"ca_cert_load\"},{\"oid\":102,\"accessId\":\"admin_group_query\"},{\"oid\":90,\"accessId\":\"trans_argument\"},{\"oid\":93,\"accessId\":\"admin_user_query\"},{\"oid\":98,\"accessId\":\"kek\"},{\"oid\":91,\"accessId\":\"admin_user\"}]}', NULL, 'ui.menu.group', '192.168.120.50', '0', 'T_ADMIN_MENU,T_ADMIN_GROUP_PRIVILEGE', 'Bob');

INSERT INTO t_cert_request VALUES ('1', '  0912', '111426', '  0912', '111426', 'V', 'D', 'test', 'WQ', '0', 'test', 'test', '2048', 'WQ', 'WQ', 'WQ', 'WQ', 'SHA256withRSA');

INSERT INTO t_key_info VALUES ('5', '  0912', '103140', '  0912', '103140', 'RSA', 'test', '2048', '1', 0x308204BD020100300D06092A864886F70D0101010500048204A7308204A30201000282010100AB100880A82ACAD22097BE89C1685306D053C28B031ECF422FF1A6C52FD4CDE1848BF9ADEF27B58BDD7C3B641F664DFAC69CC7978579611893249A068A5B4870147075FAF33FCA6F0A0640365D16A571239EF6A453C1D04E8151DE8DC449B935CB9D7067988BDB711E5B894D8B817FC79880F84C532C7CC4FA670377EC4CF595B000E86CA0606EB0FE52E8B76CFA6F4435CBD0778022CED0F6CC17AEB9791325287AC8C32CD87912FAD5062FFDB4B5A415750C93FC39915C2E5C3F5788986C9555179847596FF0004095CF526B98CE14E7675D2B359E9D369BFBC82B0647373FDFC7F71FB19919A36F6A7298FC00DFC333AF03E1F21EBE0BBD615530FD1830FB0203010001028201004A406060701E7B0130928D6B401F67829AA4F0D9E455D842F38AA2080F960073B65407E19EE08EA612529D78FD442BEF018D05F9D83EF28D773938FE43CA11AC4CC2F00328D8F4A6B4243875207CF6FD20281D09012E2C9C16F8117159D562665E668D66FA4922CC7C0FE18E5E52ACC357880BBF1D8F3EED457FB77AB380484CA5D914016491552265B174DF8A9BF464C1B96BFB5D8382D2624287820E0DA89D7E1F4D9C521AD82D3BB89D85ACEDC14A719C3B998BB93B6A35FC0C147A4B75CFC0B54F96CCE507A7C06D5300358C6C076A1FFAC19C419B7EC093B1590AEEA3EFC694AED7148DCA861D3B98173BB85A8C7B707E20D9404B609EA29EFA300D57B902818100E3524941434FDC39A79DD7A594A0CAB10B5D28C5DF0D5E6174C50D77BC39653BA2A9726ABA7AA88C893F0CB2065180013E4B1A49709894DAF94605E21EA357B00EE913DD909E395E77749EA0AE2F0CB80754B10515704D4EF9EC9F6F4FC5A8FDBB48C1EA8C3F396BA061591417A7D006A794AF2CBC589BBCC0A703FD5366CDE702818100C0A4C72855AB7CA5435C3FA1ACC33AF8D1CE7D766B49A6450A3D737B69FF5FC556DEEB626DEDAA0207CF8A4BA13C01CED6A2C4884499A8E5F3212181CFB8841FAA4C1162721D0F0624D6F853DAFA90AB6D87164A015FED49D72FBA5563BBFF64784676D2A56DC11E1E445D8DF701C6A0E92E851C86BF557F28E71529325059CD02818065C2FE5C22DFEB3C3C16E2E492E670165579FCB8A7A37A6EB2314C581F80AA1ACA3484ADD690F6470361A03349B4CE52C02A2AB859995479BA41687CD32E5820CE6BF4A2A90CCBADC8580272F7C6C430D5C749FF769B71603B6D7BE9FE920396A2726DACABDFD9BA9E6F35CB52D3A2C277DE8D94BF340373B9E28A5CAB2B485D02818100A61B2EB283B41B04F0E110E8D9EB78E4E51B7428F0778FCB85F9D8A278923F450C13A8C8B55AB13591008E118A54108705A1516EE21FD0777E3D7A03D1551B6FA64A72CEFCF03B6D23C4CC5899BF6590BB1F38C3D50D9EB005C0DC9D16231B22C96690BC2EF2450B3033E8AB2969B9983397EE24DBB70D0459036FC696E09B9D0281802A4628040D9F12C734A408A330D16FFD4333836AC0FCA1C0A059627E5D496A67E897AE90F8BE7E6F3243C12B43FF8A3414BD599CAFB35D1BD4B868BDE5591FD2705332B83589BE43F3CCE6FDC221BC94B113672CD93599FC7A1A684C7E01ADC4E42D29685042492175DD764F7260F1134EEC01FC4CB2246EA83D902D2EC9613D);
INSERT INTO t_key_info VALUES ('6', '  0912', '103140', '  0912', '103140', 'RSA', 'test', '2048', '2', 0x30820122300D06092A864886F70D01010105000382010F003082010A0282010100AB100880A82ACAD22097BE89C1685306D053C28B031ECF422FF1A6C52FD4CDE1848BF9ADEF27B58BDD7C3B641F664DFAC69CC7978579611893249A068A5B4870147075FAF33FCA6F0A0640365D16A571239EF6A453C1D04E8151DE8DC449B935CB9D7067988BDB711E5B894D8B817FC79880F84C532C7CC4FA670377EC4CF595B000E86CA0606EB0FE52E8B76CFA6F4435CBD0778022CED0F6CC17AEB9791325287AC8C32CD87912FAD5062FFDB4B5A415750C93FC39915C2E5C3F5788986C9555179847596FF0004095CF526B98CE14E7675D2B359E9D369BFBC82B0647373FDFC7F71FB19919A36F6A7298FC00DFC333AF03E1F21EBE0BBD615530FD1830FB0203010001);

