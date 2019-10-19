-- SQLPLUS system/oracle;
-- CREATE USER parking IDENTIFIED by parking;
-- GRANT CONNECT, RESOURCE TO parking;
-- CONN parking/1234;

--delete from member where useremail='baba@com';
--delete from member where useremail='lopehih@gmail.com';
commit;

-----------------------------------------------------------------------------------
SELECT usercode, useremail, userphone, username, useraddr,
  TO_CHAR(usercreateddate, 'yyyy-MM-dd hh24:mi:ss') AS created,
  TO_CHAR(userlogindate, 'yyyy-MM-dd hh24:mi:ss') AS logged,
  usersmsyn, useremailyn, useremailverified AS emailVerified, usersnsaccount AS sns,
  useroriginalfilename AS ori, userrenamedfilename AS re,
  userpw
FROM MEMBER;

select * from member m join bookmark b ON m.usercode = b.bookmarkusercode;

select ps.*,h.*, TO_CHAR(h.userhistoryparkingdate, 'yyyy-MM-dd hh24:mi:ss')  from userhistory h join parkingseoul ps on h.userhistoryparkingcode = ps.psparkingcode 
order by h.userhistoryparkingdate desc;
--update member set useremailverified=1 where useremail='baba@com';
commit;
--delete from member where usercode = '265366';
--delete from review;
commit;
SELECT b.bookmarkno AS bookmarkno, b.bookmarkusercode AS bookmarkusercode, ps.*
from bookmark b JOIN parkingseoul ps
  ON b.bookmarkparkingcode = psparkingcode;
select ps.* from bookmark b JOIN parkingseoul ps ON b.bookmarkparkingcode = psparkingcode where b.bookmarkusercode='578165';
--DELETE FROM MEMBER;
select * from member where useremail='admin@com';
SELECT * FROM TAB;
--update member set email='dbsduthd123@nate.com' where usercode='482581';
--delete from member where useremail in ('');
COMMIT;
--update member set createddate=TO_DATE('2019/08/26 01:30:44', 'yyyy/mm/dd hh24:mi:ss') where usercode='482581';
--delete from parkingowner;
--delete from parkingslot;
--alter table parkingslot modify slotbusinessno varchar2(10);
--alter table parkingowner modify ownerbusinessno varchar2(10);
--SELECT * FROM MEMBER;
--SELECT * FROM USERHISTORY;
--SELECT * FROM CAR;
--SELECT * FROM PAYMENTHISTORY;
--SELECT * FROM REVIEW;
--SELECT * FROM QNABOARD;
--SELECT * FROM NOTICE;
--SELECT * FROM BOOKMARK;
SELECT * FROM COUPON;
SELECT * FROM PARKINGSEOUL;
SELECT * FROM PARKINGOWNER;
SELECT * FROM PARKINGSLOT;

desc parkingslot;
select * from tab;
-- TABLE
--DROP TABLE MEMBER CASCADE CONSTRAINTS;
--DROP TABLE USERHISTORY CASCADE CONSTRAINTS;
--DROP TABLE CAR CASCADE CONSTRAINTS;
--DROP TABLE PAYMENTHISTORY CASCADE CONSTRAINTS;
--DROP TABLE REVIEW CASCADE CONSTRAINTS;
--DROP TABLE QNABOARD CASCADE CONSTRAINTS;
--DROP TABLE NOTICE CASCADE CONSTRAINTS;
--DROP TABLE BOOKMARK CASCADE CONSTRAINTS;
--DROP TABLE COUPON CASCADE CONSTRAINTS;
--DROP TABLE PARKINGSEOUL CASCADE CONSTRAINTS;
--DROP TABLE PARKINGOWNER CASCADE CONSTRAINTS;
--DROP TABLE PARKINGSLOT CASCADE CONSTRAINTS;
--
--DROP SEQUENCE USERHISTORY_SEQ;
--DROP SEQUENCE PAYMENTHISTORY_SEQ;
--DROP SEQUENCE REVIEW_SEQ;
--DROP SEQUENCE QNABOARD_SEQ;
--DROP SEQUENCE NOTICE_SEQ;
--DROP SEQUENCE BOOKMARK_SEQ;
--
--DROP TRIGGER USERHISTORY_trg;
--DROP TRIGGER PAYMENTHISTORY_trg;
--DROP trigger REVIEW_trg;
--DROP trigger QNABOARD_trg;
--DROP trigger NOTICE_trg;
--DROP trigger BOOKMARK_trg;

SELECT * FROM user_constraints WHERE tablename IN
  ('MEMBER', 'USERHISTORY', 'CAR', 'PAYMENTHISTORY', 'REVIEW', 'QNABOARD', 'NOTICE', 'BOOKMARK', 'COUPON');
--alter table parkingslot modify (slotbusinessno varchar2(10));
commit;
--ALTER table member add (useroriginalfilename varchar2(100), userrenamedfilename varchar2(100));

CREATE TABLE MEMBER (
  usercode varchar2(10) NOT NULL,
  useremail VARCHAR2(50) NOT NULL,
  userpw VARCHAR2(300) NOT NULL,
  userphone VARCHAR2(20) NOT NULL,
  username VARCHAR2(50) NOT NULL,
  useraddr VARCHAR2(300) NOT NULL,
  usercreateddate DATE DEFAULT SYSDATE,
  userlogindate DATE,
  usersmsyn NUMBER(1,0) NOT NULL,
  useremailyn NUMBER(1,0) NOT NULL,
  useremailverified NUMBER(1,0) DEFAULT 0,
  usersnsaccount VARCHAR(3) DEFAULT 'N/A',
  useroriginalfilename VARCHAR2(100),
  userrenamedfilename VARCHAR2(100)
);

COMMENT ON COLUMN MEMBER.usercode IS '회원코드';
COMMENT ON COLUMN MEMBER.useremail IS '이메일';
COMMENT ON COLUMN MEMBER.userpw IS '비밀번호';
COMMENT ON COLUMN MEMBER.userphone IS '폰번호';
COMMENT ON COLUMN MEMBER.username IS '회원이름';
COMMENT ON COLUMN MEMBER.useraddr IS '회원주소';
COMMENT ON COLUMN MEMBER.usercreateddate IS '가입날짜';
COMMENT ON COLUMN MEMBER.userlogindate IS '최근 로그인날짜';
COMMENT ON COLUMN MEMBER.usersmsyn IS '문자 수신여부(1/0)';
COMMENT ON COLUMN MEMBER.useremailyn IS '이메일 수신여부(1/0)';
COMMENT ON COLUMN MEMBER.useremailverified IS '이메일 인증여부(1/0)';
COMMENT ON COLUMN MEMBER.usersnsaccount IS 'SNS계정여부(Google/Facebook/Kakao/ N/A)';
COMMENT ON COLUMN MEMBER.useroriginalfilename IS '첨부파일 원래이름';
COMMENT ON COLUMN MEMBER.userrenamedfilename IS '첨부파일 변경이름';

ALTER TABLE MEMBER 
  ADD CONSTRAINT pkuser PRIMARY KEY(usercode);
ALTER TABLE MEMBER 
  ADD CONSTRAINT uquser UNIQUE (useremail);
ALTER TABLE MEMBER 
  ADD CONSTRAINT chkusersms CHECK (usersmsyn in (1,0));
ALTER TABLE MEMBER 
  ADD CONSTRAINT chkuseremail CHECK (useremailyn in (1,0));
ALTER TABLE MEMBER 
  ADD CONSTRAINT chkuserverified CHECK (useremailverified in (1,0));
ALTER TABLE MEMBER 
  ADD CONSTRAINT chkusersns CHECK (usersnsaccount in ('G', 'K', 'F', 'N/A'));


--drop table parkingseoul cascade constraints;
--ALTER TABLE PARKINGSEOUL ADD (PSNAME VARCHAR2(200) NOT NULL);

CREATE TABLE PARKINGSEOUL(
  psparkingcode varchar2(10) NOT NULL,
  psaddr VARCHAR2(200) NOT NULL,
  psname VARCHAR2(200) NOT NULL,
  pslatitude NUMBER(10,8) NOT NULL,
  pslongitude NUMBER(11,8) NOT NULL
);
COMMENT ON COLUMN PARKINGSEOUL.psparkingcode IS '주차장코드';
COMMENT ON COLUMN PARKINGSEOUL.psaddr IS '주차장주소';
COMMENT ON COLUMN PARKINGSEOUL.psname IS '주차장이름';
COMMENT ON COLUMN PARKINGSEOUL.pslatitude IS '주차장위도(0~90)';
COMMENT ON COLUMN PARKINGSEOUL.pslongitude IS '주차장경도(0~180)';
--SELECT * FROM MEMBER;
--SELECT * FROM USERHISTORY;
--SELECT * FROM CAR;
--SELECT * FROM PAYMENTHISTORY;
--SELECT * FROM REVIEW;
--SELECT * FROM QNABOARD;
--SELECT * FROM NOTICE;
--SELECT * FROM BOOKMARK;
--SELECT * FROM COUPON;
ALTER TABLE PARKINGSEOUL
  ADD CONSTRAINT pkps PRIMARY KEY(psparkingcode);

  --수정됨
CREATE TABLE PARKINGOWNER(
  ownerbusinessno varchar2(10) NOT NULL, --10자리 숫자
  ownerparkingcode varchar2(10) NOT NULL
);
COMMENT ON COLUMN PARKINGOWNER.ownerbusinessno IS '주차장사업자번호';
COMMENT ON COLUMN PARKINGOWNER.ownerparkingcode IS '주차장코드';

ALTER TABLE PARKINGOWNER
  ADD CONSTRAINT pkparkingowner PRIMARY KEY(ownerbusinessno);
ALTER TABLE PARKINGOWNER
  ADD CONSTRAINT fkparkownerps FOREIGN KEY(ownerparkingcode) REFERENCES PARKINGSEOUL(psparkingcode) ON DELETE CASCADE;

  --수저됨
CREATE TABLE PARKINGSLOT(
  slotbusinessno varchar2(10) NOT NULL,
  slotusercode varchar2(10) NOT NULL,
  slotbegintime varchar2(30),
  slotendtime varchar2(30)
);
COMMENT ON COLUMN PARKINGSLOT.slotbusinessno IS '주차장사업자번호';
COMMENT ON COLUMN PARKINGSLOT.slotusercode IS '회원코드';
COMMENT ON COLUMN PARKINGSLOT.x`slotbegintime IS '주차시작시간';
COMMENT ON COLUMN PARKINGSLOT.slotendtime IS '주차종료시간';

ALTER TABLE PARKINGSLOT
  ADD CONSTRAINT fkparkingslotowner FOREIGN KEY(slotbusinessno) REFERENCES PARKINGOWNER(ownerbusinessno) ON DELETE CASCADE;

ALTER TABLE PARKINGSLOT
  ADD CONSTRAINT fkparkingslotuser FOREIGN KEY(slotusercode) REFERENCES MEMBER(usercode) ON DELETE CASCADE;



--selectUserHistory:
--select h.* from userhistory h join parkingseoul ps
--  on h.userhistoryparkingcode = ps.psparkingcode
--  where h.userhistoryusercode=? and ps.parkingcode=?;
--selectParking:
--select ps.* from userhistory h join parkingseoul ps
--  on h.userhistoryparkingcode = ps.psparkingcode
--  where h.userhistoryusercode=? and ps.parkingcode=?;
--selectUserHistoryParkingList:
--select ps.* from userhistory h join parkingseoul ps
--  on h.userhistoryparkingcode = ps.psparkingcode
--  where h.userhistoryusercode=?;
--usercode 578165
--parkingcode
--  1052723
--  1423842
--  1052741
--  1052737
--insert into userhistory values(DEFAULT, '578165', '1052723', DEFAULT);
--insert into userhistory values(DEFAULT, '578165', '1423842', DEFAULT);
--insert into userhistory values(DEFAULT, '578165', '1052741', DEFAULT);
--insert into userhistory values(DEFAULT, '578165', '1052737', DEFAULT);
select * from userhistory;
commit;

select userhistoryno, userhistoryusercode as Usercode, m.username, 
  userhistoryparkingcode AS parkingcode, 
  TO_CHAR(userhistoryparkingdate, 'yyyy-MM-dd hh24:mi:ss') AS parkingdate,
  TRIM(to_char(userhistorypayment, '9,999,999.00'))|| '\\' AS payment
 from userhistory h join member m on h.userhistoryusercode = m.usercode;

commit;
--DROP TABLE USERHISTORY CASCADE CONSTRAINTS;
--drop sequence userhistory_seq;
--drop trigger userhistory_trg;

CREATE TABLE USERHISTORY(
  userhistoryno NUMBER(5) NOT NULL,
  userhistoryusercode varchar2(10) NOT NULL,
  userhistoryparkingcode varchar2(10) NOT NULL,
  userhistoryparkingdate DATE DEFAULT SYSDATE,
  userhistorypayment NUMBER(7) DEFAULT 0 
);
COMMENT ON COLUMN USERHISTORY.userhistoryno IS '이용내역번호';
COMMENT ON COLUMN USERHISTORY.userhistoryusercode IS '회원코드';
COMMENT ON COLUMN USERHISTORY.userhistoryparkingcode IS '주차장코드';
COMMENT ON COLUMN USERHISTORY.userhistoryparkingdate IS '주차날짜';
COMMENT ON COLUMN USERHISTORY.userhistorypayment IS '주차요금 결제액';

CREATE SEQUENCE USERHISTORY_SEQ START WITH 1;

CREATE OR REPLACE TRIGGER USERHISTORYTRG
BEFORE INSERT ON USERHISTORY
FOR EACH ROW
BEGIN
 SELECT USERHISTORY_SEQ.NEXTVAL
 INTO :NEW.userhistoryno
 FROM DUAL;
END;
/

ALTER TABLE USERHISTORY
  ADD CONSTRAINT pkuserhist PRIMARY KEY(userhistoryno);
ALTER TABLE USERHISTORY
  ADD CONSTRAINT fkuserhistmember FOREIGN KEY(userhistoryusercode) REFERENCES MEMBER(usercode)
  ON DELETE CASCADE;
ALTER TABLE USERHISTORY
  ADD CONSTRAINT fkuserhistps FOREIGN KEY(userhistoryparkingcode)
        REFERENCES PARKINGSEOUL(psparkingcode)
  ON DELETE CASCADE;


select * from review r join userhistory h 
  on r.reviewuserhistoryno = h.userhistoryno 
where h.userhistoryusercode ='578165';
--drop table review cascade constraints;
--drop sequence review_seq;
--drop trigger review_trg;
--insert into review values(DEFAULT, '1', 'Best experience!', 'Ive never seen such a clean parking lot before... I will use this place often! It was a delight experience', DEFAULT, 5);
--insert into review values(DEFAULT, '2', 'eh', 'It was alright. I was about to pick up my family and I had to park my car at least for one hour. Luckily, I found this Parking app which provided me with the realtime parking locations around me. But it was way too expensive. So I give you 1 star ;)', DEFAULT, 1);
--insert into review values(DEFAULT, '4', 'Beware', 'The guy working there is rude. He yelled at me and tried to charge me 200 bucks per second. Definitely not worth your time and money!', DEFAULT, 4);
commit;

CREATE TABLE REVIEW(
  reviewno NUMBER(5) NOT NULL,
  reviewuserhistoryno varchar2(7) NOT NULL,
  reviewtitle VARCHAR2(50) NOT NULL,
  reviewcontent VARCHAR2(300) NOT NULL,
  reviewcreateddate DATE DEFAULT SYSDATE,
  reviewrating NUMBER(1) NOT NULL,
  reviewreadcount NUMBER DEFAULT 0
);

COMMENT ON COLUMN REVIEW.reviewno IS '코드번호';
COMMENT ON COLUMN REVIEW.reviewuserhistoryno IS '이용내역번호';
COMMENT ON COLUMN REVIEW.reviewtitle IS '리뷰 제목';
COMMENT ON COLUMN REVIEW.reviewcontent IS '리뷰 작성글';
COMMENT ON COLUMN REVIEW.reviewcreateddate IS '작성날짜';
COMMENT ON COLUMN REVIEW.reviewrating IS '평점(1~5 정수)';
COMMENT ON COLUMN REVIEW.reviewreadcount IS '조회수';

CREATE SEQUENCE REVIEW_SEQ START WITH 1;

CREATE OR REPLACE TRIGGER REVIEWTRG
BEFORE INSERT ON REVIEW
FOR EACH ROW
BEGIN
 SELECT REVIEW_SEQ.NEXTVAL
 INTO :NEW.reviewno
 FROM DUAL;
END;
/

ALTER TABLE REVIEW
  ADD CONSTRAINT pkreview PRIMARY KEY(reviewno);
ALTER TABLE REVIEW
  ADD CONSTRAINT fkreviewuserhist FOREIGN KEY(reviewno)
        REFERENCES userhistory(userhistoryno)
  ON DELETE CASCADE;
ALTER TABLE REVIEW
  ADD CONSTRAINT chkreviewrating CHECK (reviewrating in (1,2,3,4,5));


--DROP TABLE REVIEWPIC CASCADE CONTRAINTS

--
--drop table qnaboard cascade constraints;
--commit;
CREATE TABLE QNABOARD(
  qnano NUMBER(5) NOT NULL,
  qnatitle VARCHAR2(50) NOT NULL,
  qnausercode varchar2(10) NOT NULL,
  qnacontent VARCHAR2(300) NOT NULL,
  qnacreateddate DATE DEFAULT SYSDATE,
  qnareadcount NUMBER DEFAULT 0
);

COMMENT ON COLUMN QNABOARD.qnano IS '문의글번호';
COMMENT ON COLUMN QNABOARD.qnatitle IS '문의글 제목';
COMMENT ON COLUMN QNABOARD.qnausercode IS '회원코드';
COMMENT ON COLUMN QNABOARD.qnacontent IS '문의글 내용';
COMMENT ON COLUMN QNABOARD.qnacreateddate IS '작성날짜';
COMMENT ON COLUMN QNABOARD.qnareadcount IS '조회수';

CREATE SEQUENCE QNABOARD_SEQ START WITH 1;

CREATE OR REPLACE TRIGGER QNABOARDTRG
BEFORE INSERT ON QNABOARD
FOR EACH ROW
BEGIN
 SELECT QNABOARD_SEQ.NEXTVAL
 INTO :NEW.qnano
 FROM DUAL;
END;
/
commit;

ALTER TABLE QNABOARD
  ADD CONSTRAINT pkqna PRIMARY KEY(qnano);
ALTER TABLE QNABOARD
  ADD CONSTRAINT fkqnamember FOREIGN KEY(qnausercode) REFERENCES MEMBER(usercode)
  ON DELETE CASCADE;


--DROP TABLE QNABOARD cascade constraints;
--DROP TRIGGER QNABOARDTRG;
--DROP SEQUENCE QNABOARD_SEQ;
--select * from qnaboard;
--select count(*) from qnaboard;
--delete from qnaboard;
commit;

insert into qnaboard values(DEFAULT,'atitle', '181862', 'this is a content1', null,null, default, default);
insert into qnaboard values(DEFAULT,'btitle', '181862', 'this is a content2', null,null, default, default);
insert into qnaboard values(DEFAULT,'ctitle', '181862', 'this is a content3', null,null, default, default);
insert into qnaboard values(DEFAULT,'dtitle', '181862', 'this is a content4', null,null, default, default);
insert into qnaboard values(DEFAULT,'etitle', '181862', 'this is a content5', null,null, default, default);
insert into qnaboard values(DEFAULT,'ftitle', '181862', 'this is a content6', null,null, default, default);
insert into qnaboard values(DEFAULT,'gtitle', '181862', 'this is a content7', null,null, default, default);
insert into qnaboard values(DEFAULT,'htitle', '181862', 'this is a content8', null,null, default, default);
insert into qnaboard values(DEFAULT,'ititle', '181862', 'this is a content9', null,null, default, default);
insert into qnaboard values(DEFAULT,'jtitle', '181862', 'this is a content10', null,null, default, default);
insert into qnaboard values(DEFAULT,'ktitle', '181862', 'this is a content11', null,null, default, default);
insert into qnaboard values(DEFAULT,'ltitle', '181862', 'this is a content12', null,null, default, default);
insert into qnaboard values(DEFAULT,'mtitle', '181862', 'this is a content13', null,null, default, default);
insert into qnaboard values(DEFAULT,'ntitle', '181862', 'this is a content14', null,null, default, default);
insert into qnaboard values(DEFAULT,'otitle', '181862', 'this is a content15', null,null, default, default);
insert into qnaboard values(DEFAULT,'ptitle', '181862', 'this is a content16', null,null, default, default);
insert into qnaboard values(DEFAULT,'qtitle', '181862', 'this is a content17', null,null, default, default);
insert into qnaboard values(DEFAULT,'rtitle', '181862', 'this is a content18', null,null, default, default);
insert into qnaboard values(DEFAULT,'stitle', '181862', 'this is a content19', null,null, default, default);
insert into qnaboard values(DEFAULT,'ttitle', '181862', 'this is a content20', null,null, default, default);
insert into qnaboard values(DEFAULT,'utitle', '181862', 'this is a content21', null,null, default, default);
insert into qnaboard values(DEFAULT,'vtitle', '181862', 'this is a content22', null,null, default, default);
insert into qnaboard values(DEFAULT,'wtitle', '181862', 'this is a content23', null,null, default, default);
insert into qnaboard values(DEFAULT,'xtitle', '181862', 'this is a content24', null,null, default, default);
insert into qnaboard values(DEFAULT,'ytitle', '181862', 'this is a content25', null,null, default, default);
insert into qnaboard values(DEFAULT,'ztitle', '181862', 'this is a content26', null,null, default, default);
insert into qnaboard values(DEFAULT,'zzztitle', '181862', 'this is a content27', null,null, default, default);
commit;


--DROP TABLE NOTICE;

CREATE TABLE NOTICE(
  noticeno NUMBER(5) NOT NULL,
  noticeusercode varchar2(6) NOT NULL,
  noticetitle VARCHAR2(50) NOT NULL,
  noticecontent VARCHAR2(300) NOT NULL,
  noticecreateddate DATE DEFAULT SYSDATE,
  noticereadcount NUMBER DEFAULT 0
);
COMMENT ON COLUMN NOTICE.noticeno IS '공지사항글번호';
COMMENT ON COLUMN NOTICE.noticeusercode IS '회원코드';
COMMENT ON COLUMN NOTICE.noticetitle IS '공지사항 제목';
COMMENT ON COLUMN NOTICE.noticecontent IS '공지사항 내용';
COMMENT ON COLUMN NOTICE.noticecreateddate IS '작성날짜';
COMMENT ON COLUMN NOTICE.noticereadcount IS '조회수';

--drop sequence notice_seq;
--drop trigger notice_trg;

CREATE SEQUENCE NOTICE_SEQ START WITH 1;

CREATE OR REPLACE TRIGGER NOTICETRG
BEFORE INSERT ON NOTICE
FOR EACH ROW
BEGIN
 SELECT NOTICE_SEQ.NEXTVAL
 INTO :NEW.noticeno
 FROM DUAL;
END;
/

ALTER TABLE NOTICE 
  ADD CONSTRAINT pknotice PRIMARY KEY(noticeno);
ALTER TABLE NOTICE
  ADD CONSTRAINT fknoticemember FOREIGN KEY(noticeusercode) REFERENCES MEMBER(usercode)
  ON DELETE CASCADE;

    
--drop table bookmark cascade constraints;
--drop sequence bookmark_seq;
--drop trigger bookmark_trg;
select b.*, m.username, m.useremail from bookmark b join member m  on b.bookmarkusercode = m.usercode;
--insert into bookmark values(DEFAULT,'578165', '1033125');
--insert into bookmark values(DEFAULT,'578165', '1042423');
--delete from bookmark where bookmarkusercode='578165';

COMMIT;

CREATE TABLE BOOKMARK(
  bookmarkno NUMBER(3) NOT NULL,
  bookmarkusercode varchar2(10) NOT NULL,
  bookmarkparkingcode varchar2(10) NOT NULL
);
COMMENT ON COLUMN BOOKMARK.bookmarkno IS '북마크번호';
COMMENT ON COLUMN BOOKMARK.bookmarkusercode IS '회원코드';
COMMENT ON COLUMN BOOKMARK.bookmarkparkingcode IS '주차코드';


CREATE SEQUENCE BOOKMARK_SEQ START WITH 1;

CREATE OR REPLACE TRIGGER BOOKMARKTRG
BEFORE INSERT ON BOOKMARK
FOR EACH ROW
BEGIN
 SELECT BOOKMARK_SEQ.NEXTVAL
 INTO :NEW.bookmarkno
 FROM DUAL;
END;
/

ALTER TABLE BOOKMARK 
  ADD CONSTRAINT pkbookmark PRIMARY KEY(bookmarkusercode, bookmarkparkingcode);
ALTER TABLE BOOKMARK
  ADD CONSTRAINT fkbookmarkmember FOREIGN KEY(bookmarkusercode) REFERENCES MEMBER(usercode)
  ON DELETE CASCADE;


--DROP table coupon cascade constraints;

CREATE TABLE COUPON(
  couponcode varchar2(20) NOT NULL,
  couponusercode varchar2(10) NOT NULL,
  coupondiscountrate NUMBER(2) NOT NULL,
  couponduration NUMBER(1) NOT NULL,
  couponexpiredyn NUMBER(1,0)
);
COMMENT ON COLUMN COUPON.couponcode IS '쿠폰번호';
COMMENT ON COLUMN COUPON.couponusercode IS '회원코드';
COMMENT ON COLUMN COUPON.coupondiscountrate IS '할인율';
COMMENT ON COLUMN COUPON.couponduration IS '쿠폰기한';
COMMENT ON COLUMN COUPON.couponexpiredyn IS '쿠폰 사용기한 초과여부(1/0)'; 

ALTER TABLE COUPON
  ADD CONSTRAINT pkcoupon PRIMARY KEY(couponcode);
ALTER TABLE COUPON
  ADD CONSTRAINT fkcouponmember FOREIGN KEY(couponusercode) REFERENCES MEMBER(usercode)
  ON DELETE CASCADE;
ALTER TABLE COUPON
  ADD CONSTRAINT chkcouponexpiredyn CHECK (couponexpiredyn in(1, 0));

--drop table car CASCADE CONSTRAINTS;

COMMIT;
