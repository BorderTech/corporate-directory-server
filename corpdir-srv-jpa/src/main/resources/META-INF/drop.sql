
alter table Position drop constraint FK_Position_to_Position;
alter table Position drop constraint FK_Position_to_PositionType;
alter table OrgUnit drop constraint FK_OrgUnit_to_Type;
alter table OrgUnit drop constraint FK_OrgUnit_to_OrgUnit;
alter table OrgUnit drop constraint FK_OrgUnit_to_MangerPosition;
alter table Position drop constraint FK_Position_to_OrgUnit;
alter table OrgUnit_Position drop constraint UK_OrgUnit_Position;
alter table OrgUnit_Position drop constraint FK_OrgUnit_Position_to_Position;
alter table OrgUnit_Position drop constraint FK_OrgUnit_Position_to_OrgUnit;
alter table Position_OrgUnit drop constraint UK_Position_OrgUnit;
alter table Position_OrgUnit drop constraint FK_Position_OrgUnit_to_OrgUnit;
alter table Position_OrgUnit drop constraint FK_Position_OrgUnit_to_Position;
alter table Location drop constraint FK_Location_to_Location;
alter table Contact drop constraint FK_Contact_to_Location;
alter table Contact drop constraint FK_Contact_to_Image;
alter table Contact_Channel drop constraint UK_Contact_Channel;
alter table Contact_Channel drop constraint FK_Contact_Channel_to_Channel;
alter table Contact_Channel drop constraint FK_Contact_Channel_to_Contact;
alter table Contact_Position drop constraint FK_Contact_Position_to_Contact;
alter table Contact_Position drop constraint FK_Contact_Position_to_Position;
alter table Position_Contact drop constraint FK_Position_Contact_to_Contact;
alter table Position_Contact drop constraint FK_Position_Contact_to_Position;

drop table PositionType;
drop table Position;
drop table UnitType;
drop table OrgUnit;
drop table OrgUnit_Position;
drop table Position_OrgUnit;
drop table Location;
drop table Image;
drop table Channel;
drop table Contact;
drop table Contact_Channel;
drop table Contact_Position;
drop table Position_Contact;
