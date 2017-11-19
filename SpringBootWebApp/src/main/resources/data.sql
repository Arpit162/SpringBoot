
insert into person(PERSON_ID, NAME,SPECIALITY,ROLE,YOB,RECORDS,PRESCRIPTIONS,PASSWORD) 
values(1,'Reddy','neural','patient','1975','Record','Prescription','123');
insert into person(PERSON_ID, NAME,SPECIALITY,ROLE,YOB,PASSWORD) 
values(2,'ReddyDoc','neural','doctor','1970','123');
insert into person(PERSON_ID, NAME,SPECIALITY,ROLE,YOB,PASSWORD) 
values(3,'ReddyPharma','all','pharmacist','1980','123');
insert into person(PERSON_ID, NAME,SPECIALITY,ROLE,YOB,RECORDS,PRESCRIPTIONS,PASSWORD) 
values(4,'Gupta','all','patient','1965','Record','Prescription','123');

insert into AUTHORIZATIONS(AUTH_ID,REQUESTER_ID,APPROVER_ID,REQUEST_TYPE) 
values(1,2,1,'requested');
insert into AUTHORIZATIONS(AUTH_ID,REQUESTER_ID,APPROVER_ID,REQUEST_TYPE) 
values(2,3,1,'requested');