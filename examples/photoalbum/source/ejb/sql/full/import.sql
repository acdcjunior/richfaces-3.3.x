INSERT INTO User(id, firstname, secondname, email, login, passwordHash, birthdate, sex, hasAvatar, preDefined) VALUES (1,  'Andrey', 'Markhel', 'amarkhel@exadel.com',  'amarkhel', '8cb2237d0679ca88db6464eac60da96345513964',  '1985-01-08', 0, false, true);
INSERT INTO User(id, firstname, secondname, email, login, passwordHash, birthdate, sex, hasAvatar, preDefined) VALUES (2,  'Nick',   'Curtis',  'nkurtis@iba.com',      'Viking',   '8cb2237d0679ca88db6464eac60da96345513964',  '1978-01-08', 1, false, true);
INSERT INTO User(id, firstname, secondname, email, login, passwordHash, birthdate, sex, hasAvatar, preDefined) VALUES (3,  'John',   'Smith',   'jsmith@jboss.com',     'Noname',   '8cb2237d0679ca88db6464eac60da96345513964',  '1970-01-08', 1, false, true);

INSERT INTO User(id, firstname, secondname, email, login, passwordHash, birthdate, sex, hasAvatar, preDefined) VALUES (10, 'John',   'Smith',   'jsmith_10@jboss.com',     'user_for_add',   '8cb2237d0679ca88db6464eac60da96345513964',  '1970-01-08', 1, false, false);
INSERT INTO User(id, firstname, secondname, email, login, passwordHash, birthdate, sex, hasAvatar, preDefined) VALUES (11, 'John',   'Smith',   'jsmith_11@jboss.com',     'user_for_del',   '8cb2237d0679ca88db6464eac60da96345513964',  '1970-01-08', 1, false, false);
INSERT INTO User(id, firstname, secondname, email, login, passwordHash, birthdate, sex, hasAvatar, preDefined) VALUES (12, 'John',   'Smith',   'jsmith_12@jboss.com',     'user_for_dnd',  '8cb2237d0679ca88db6464eac60da96345513964',  '1970-01-08', 1, false, false);

INSERT INTO Shelf(id, name, description, owner_id, created, shared) VALUES (1, 'Nature',       'Nature pictures',        1, '2009-12-18', true);
INSERT INTO Shelf(id, name, description, owner_id, created, shared) VALUES (2, 'Sport & Cars', 'Sport & Cars pictures',  1, '2009-12-18', true);
INSERT INTO Shelf(id, name, description, owner_id, created, shared) VALUES (3, 'Portrait',     'Human faces',        2, '2009-12-18', true);
INSERT INTO Shelf(id, name, description, owner_id, created, shared) VALUES (4, 'Monuments',    'Monuments pictures',     3, '2009-12-18', true);
INSERT INTO Shelf(id, name, description, owner_id, created, shared) VALUES (5, 'Water',        'Water pictures',         3, '2009-12-18', true);

INSERT INTO Shelf(id, name, description, owner_id, created, shared) VALUES (100, 'MyShelf 100',     'MyShelf',               10, '2009-12-18', false);
INSERT INTO Shelf(id, name, description, owner_id, created, shared) VALUES (101, 'MyShelf 101',     'MyShelf',               10, '2009-12-18', false);
INSERT INTO Shelf(id, name, description, owner_id, created, shared) VALUES (110, 'MyShelf 110',     'MyShelf',               11, '2009-12-18', false);
INSERT INTO Shelf(id, name, description, owner_id, created, shared) VALUES (111, 'MyShelf 111',     'MyShelf',               11, '2009-12-18', false);
INSERT INTO Shelf(id, name, description, owner_id, created, shared) VALUES (120, 'MyShelf 120',     'MyShelf',               12, '2009-12-18', false);
INSERT INTO Shelf(id, name, description, owner_id, created, shared) VALUES (121, 'MyShelf 121',     'MyShelf',               12, '2009-12-18', false);

---------------------------------------------------------------------
-- ALBUM - Animals"
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (0, 'Animals', 'Animals pictures',  1, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (0,  'Animals');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (0, '1750979205_6e51b47ce9_o.jpg', '1750979205_6e51b47ce9_o.jpg', 'Animals - 1750979205_6e51b47ce9_o.jpg image',  '2009-12-18', 0, 'Canon EOS 450D', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(0, 0);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (0, '1985-01-08', 'this is extremely Good:) Congratulations!',0, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (1, '1906662004_655d0f6ccf_o.jpg', '1906662004_655d0f6ccf_o.jpg', 'Animals - 1906662004_655d0f6ccf_o.jpg image',  '2009-12-18', 0, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(0, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (1, '1985-01-08', 'nice shot =) ',1, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (2, '1985-01-08', 'nice shot =) ',1, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (2, '2090459727_f2888e5cbe_o.jpg', '2090459727_f2888e5cbe_o.jpg', 'Animals - 2090459727_f2888e5cbe_o.jpg image',  '2009-12-18', 0, 'Canon PowerShot SX110 IS', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(0, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (3, '2297752925_de29b5fb10_o.jpg', '2297752925_de29b5fb10_o.jpg', 'Animals - 2297752925_de29b5fb10_o.jpg image',  '2009-12-18', 0, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(0, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (3, '1985-01-08', 'that is a beautiful flower with great colours ',3, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (4, '1985-01-08', 'Fantastic job. Great light and color! Great shot!',3, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (5, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',3, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (6, '1985-01-08', 'Gorgeous! Lovely color!',3, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (4, '2298556444_2151b7a6c4_o.jpg', '2298556444_2151b7a6c4_o.jpg', 'Animals - 2298556444_2151b7a6c4_o.jpg image',  '2009-12-18', 0, 'Sony Alpha DSLR-A350', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(0, 4);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (7, '1985-01-08', 'Stunning capture! :-)',4, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (8, '1985-01-08', 'Wonderful.',4, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (5, '2508246015_313952406c_o.jpg', '2508246015_313952406c_o.jpg', 'Animals - 2508246015_313952406c_o.jpg image',  '2009-12-18', 0, 'Canon Digital IXUS 80 IS (PowerShot SD1100 IS)', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(0, 5);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (9, '1985-01-08', 'Gorgeous! Lovely color!',5, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (6, '2521898117_f2ebf233c9_o.jpg', '2521898117_f2ebf233c9_o.jpg', 'Animals - 2521898117_f2ebf233c9_o.jpg image',  '2009-12-18', 0, 'Pentax Optio E40', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(0, 6);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (10, '1985-01-08', 'this is extremely Good:) Congratulations!',6, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (11, '1985-01-08', 'fantastic shot !!!!!!',6, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (7, '308709862_f8f7fbcec4_b.jpg', '308709862_f8f7fbcec4_b.jpg', 'Animals - 308709862_f8f7fbcec4_b.jpg image',  '2009-12-18', 0, 'Olympus Stylus mju 1040', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(0, 7);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (12, '1985-01-08', 'Superb Shot and so beautiful Colors !!! ',7, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (13, '1985-01-08', 'really pretty. it looks like there is a lady in the _center_, blowing kisses!!',7, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (8, '3390059723_4b2883ee59_o.jpg', '3390059723_4b2883ee59_o.jpg', 'Animals - 3390059723_4b2883ee59_o.jpg image',  '2009-12-18', 0, 'BBK DP810', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(0, 8);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (14, '1985-01-08', 'Wonderful.',8, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (15, '1985-01-08', 'Amazing shot..',8, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (16, '1985-01-08', 'Wow!! Macro stupenda!!! Complimenti! ',8, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (17, '1985-01-08', 'Fantastic job. Great light and color! Great shot!',8, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (9, '3390865548_e394404198_o.jpg', '3390865548_e394404198_o.jpg', 'Animals - 3390865548_e394404198_o.jpg image',  '2009-12-18', 0, 'BenQ DC E800', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(0, 9);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (18, '1985-01-08', 'that is a beautiful flower with great colours ',9, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (10, '3390879696_059660d45c_o.jpg', '3390879696_059660d45c_o.jpg', 'Animals - 3390879696_059660d45c_o.jpg image',  '2009-12-18', 0, 'Konica Minolta', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(0, 10);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (19, '1985-01-08', 'Perfecft!',10, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (20, '1985-01-08', 'whoah ! wonderful',10, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (21, '1985-01-08', 'Wonderful.',10, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (22, '1985-01-08', 'Amazing shot..',10, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (11, '3390886140_b05ae25b14_o.jpg', '3390886140_b05ae25b14_o.jpg', 'Animals - 3390886140_b05ae25b14_o.jpg image',  '2009-12-18', 0, 'Panasonic', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(0, 11);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (23, '1985-01-08', 'this is extremely Good:) Congratulations!',11, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (24, '1985-01-08', 'Beautiful ^Flower^...great Macro....Excellent !!!',11, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (25, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',11, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (26, '1985-01-08', 'Bellissima macro!',11, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (12, '3413588364_dd0d602b9b_o.jpg', '3413588364_dd0d602b9b_o.jpg', 'Animals - 3413588364_dd0d602b9b_o.jpg image',  '2009-12-18', 0, 'LG LDC-A310', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(0, 12);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (27, '1985-01-08', 'Superb Shot and so beautiful Colors !!! ',12, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (28, '1985-01-08', '++Beautiful',12, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (29, '1985-01-08', 'Beautiful colours. Nice close up. ',12, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (30, '1985-01-08', 'Beautiful colours. Nice close up. ',12, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (13, '3415160108_c22eb69f37_o.jpg', '3415160108_c22eb69f37_o.jpg', 'Animals - 3415160108_c22eb69f37_o.jpg image',  '2009-12-18', 0, 'Canon EOS 450D', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(0, 13);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (31, '1985-01-08', 'Wonderful.',13, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (14, '3415165572_f6d48c2a21_o.jpg', '3415165572_f6d48c2a21_o.jpg', 'Animals - 3415165572_f6d48c2a21_o.jpg image',  '2009-12-18', 0, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(0, 14);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (32, '1985-01-08', 'Beautiful ^Flower^...great Macro....Excellent !!!',14, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (33, '1985-01-08', 'Wow!! Macro stupenda!!! Complimenti! ',14, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (15, '839245545_5db77619d5_o.jpg', '839245545_5db77619d5_o.jpg', 'Animals - 839245545_5db77619d5_o.jpg image',  '2009-12-18', 0, 'Canon PowerShot SX110 IS', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(0, 15);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (34, '1985-01-08', 'Beautiful colours. Nice close up. ',15, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (35, '1985-01-08', 'Gorgeous! Lovely color!',15, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (36, '1985-01-08', 'love every thing about this picture, really beautiful... :))',15, 2);
UPDATE Album set coveringImage_id=15 where id = 0;

---------------------------------------------------------------------
-- ALBUM - Cars"
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (1, 'Cars', 'Cars pictures',  2, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (1,  'Cars');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (16, '190193308_ce2a4de5fa_o.jpg', '190193308_ce2a4de5fa_o.jpg', 'Cars - 190193308_ce2a4de5fa_o.jpg image',  '2009-12-18', 1, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(1, 16);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (37, '1985-01-08', 'Amazing shot..',16, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (38, '1985-01-08', 'Wonderful.',16, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (17, '1941230817_bcce17b8ef_o.jpg', '1941230817_bcce17b8ef_o.jpg', 'Cars - 1941230817_bcce17b8ef_o.jpg image',  '2009-12-18', 1, 'Sony Alpha DSLR-A350', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(1, 17);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (39, '1985-01-08', 'whoah ! wonderful',17, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (18, '2151423750_129317a034_o.jpg', '2151423750_129317a034_o.jpg', 'Cars - 2151423750_129317a034_o.jpg image',  '2009-12-18', 1, 'Canon Digital IXUS 80 IS (PowerShot SD1100 IS)', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(1, 18);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (19, '2233985073_9a3fd7d3ac_b.jpg', '2233985073_9a3fd7d3ac_b.jpg', 'Cars - 2233985073_9a3fd7d3ac_b.jpg image',  '2009-12-18', 1, 'Pentax Optio E40', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(1, 19);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (40, '1985-01-08', 'Amazing shot..',19, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (41, '1985-01-08', 'Perfecft!',19, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (42, '1985-01-08', 'I Think this is Art!',19, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (20, '2386071696_2b4e84eddb_o.jpg', '2386071696_2b4e84eddb_o.jpg', 'Cars - 2386071696_2b4e84eddb_o.jpg image',  '2009-12-18', 1, 'Olympus Stylus mju 1040', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(1, 20);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (43, '1985-01-08', 'Stunning capture! :-)',20, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (44, '1985-01-08', 'Bell??sima.!!! saludos.',20, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (45, '1985-01-08', 'Gorgeous! Lovely color!',20, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (46, '1985-01-08', 'Wonderful.',20, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (21, '3089719367_a03a2b55a4_b.jpg', '3089719367_a03a2b55a4_b.jpg', 'Cars - 3089719367_a03a2b55a4_b.jpg image',  '2009-12-18', 1, 'BBK DP810', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(1, 21);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (47, '1985-01-08', 'Beautiful ^Flower^...great Macro....Excellent !!!',21, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (48, '1985-01-08', 'Stunning capture! :-)',21, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (49, '1985-01-08', '++Beautiful',21, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (22, '3200944376_2867888262_b.jpg', '3200944376_2867888262_b.jpg', 'Cars - 3200944376_2867888262_b.jpg image',  '2009-12-18', 1, 'BenQ DC E800', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(1, 22);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (50, '1985-01-08', 'this is extremely Good:) Congratulations!',22, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (51, '1985-01-08', 'Gorgeous! Lovely color!',22, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (52, '1985-01-08', 'fantastic shot !!!!!!',22, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (53, '1985-01-08', 'Bellissima macro!',22, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (23, '3286825259_2a927e8ec1_o.jpg', '3286825259_2a927e8ec1_o.jpg', 'Cars - 3286825259_2a927e8ec1_o.jpg image',  '2009-12-18', 1, 'Konica Minolta', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(1, 23);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (54, '1985-01-08', 'love every thing about this picture, really beautiful... :))',23, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (55, '1985-01-08', 'Wonderful.',23, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (24, '3287637120_c1b3495371_o.jpg', '3287637120_c1b3495371_o.jpg', 'Cars - 3287637120_c1b3495371_o.jpg image',  '2009-12-18', 1, 'Panasonic', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(1, 24);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (56, '1985-01-08', 'Wonderful.',24, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (25, '3327664478_7f77093f52_o.jpg', '3327664478_7f77093f52_o.jpg', 'Cars - 3327664478_7f77093f52_o.jpg image',  '2009-12-18', 1, 'LG LDC-A310', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(1, 25);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (57, '1985-01-08', 'Fantastic job. Great light and color! Great shot!',25, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (58, '1985-01-08', 'nice shot =) ',25, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (59, '1985-01-08', 'Bellissima macro!',25, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (26, '361088850_73d4bef19a_o.jpg', '361088850_73d4bef19a_o.jpg', 'Cars - 361088850_73d4bef19a_o.jpg image',  '2009-12-18', 1, 'Canon EOS 450D', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(1, 26);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (60, '1985-01-08', 'really pretty. it looks like there is a lady in the _center_, blowing kisses!!',26, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (61, '1985-01-08', 'Gorgeous! Lovely color!',26, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (62, '1985-01-08', 'Very *lovely*',26, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (63, '1985-01-08', 'Gorgeous! Lovely color!',26, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (27, '361091514_20cd7e7eea_o.jpg', '361091514_20cd7e7eea_o.jpg', 'Cars - 361091514_20cd7e7eea_o.jpg image',  '2009-12-18', 1, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(1, 27);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (64, '1985-01-08', 'Superb Shot and so beautiful Colors !!! ',27, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (65, '1985-01-08', 'Wonderful.',27, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (66, '1985-01-08', 'really pretty. it looks like there is a lady in the _center_, blowing kisses!!',27, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (67, '1985-01-08', 'whoah ! wonderful',27, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (28, '361092609_25d6d4bd90_o.jpg', '361092609_25d6d4bd90_o.jpg', 'Cars - 361092609_25d6d4bd90_o.jpg image',  '2009-12-18', 1, 'Canon PowerShot SX110 IS', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(1, 28);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (68, '1985-01-08', 'Beautiful colours. Nice close up. ',28, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (69, '1985-01-08', 'Very *lovely*',28, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (70, '1985-01-08', 'I Think this is Art!',28, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (29, '400440032_a63fe65cab_o.jpg', '400440032_a63fe65cab_o.jpg', 'Cars - 400440032_a63fe65cab_o.jpg image',  '2009-12-18', 1, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(1, 29);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (71, '1985-01-08', 'Gorgeous! Lovely color!',29, 2);
UPDATE Album set coveringImage_id=29 where id = 1;

---------------------------------------------------------------------
-- ALBUM - Monuments and just buildings"
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (2, 'Monuments and just buildings', 'Monuments and just buildings pictures',  4, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (2,  'Monuments and just buildings');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (30, '05[303x457].jpg', '05[303x457].jpg', 'Monuments and just buildings - 05[303x457].jpg image',  '2009-12-18', 2, 'Sony Alpha DSLR-A350', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 30);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (72, '1985-01-08', 'Gorgeous! Lovely color!',30, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (73, '1985-01-08', 'this is extremely Good:) Congratulations!',30, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (74, '1985-01-08', 'Superb Shot and so beautiful Colors !!! ',30, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (31, '07[303x457].jpg', '07[303x457].jpg', 'Monuments and just buildings - 07[303x457].jpg image',  '2009-12-18', 2, 'Canon Digital IXUS 80 IS (PowerShot SD1100 IS)', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 31);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (32, '1805365000_ca64d20b10_o.jpg', '1805365000_ca64d20b10_o.jpg', 'Monuments and just buildings - 1805365000_ca64d20b10_o.jpg image',  '2009-12-18', 2, 'Pentax Optio E40', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 32);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (33, '1[303x457].jpg', '1[303x457].jpg', 'Monuments and just buildings - 1[303x457].jpg image',  '2009-12-18', 2, 'Olympus Stylus mju 1040', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 33);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (75, '1985-01-08', 'really pretty. it looks like there is a lady in the _center_, blowing kisses!!',33, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (76, '1985-01-08', 'Bellissima macro!',33, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (34, '2099090261_1efb89ae95_o.jpg', '2099090261_1efb89ae95_o.jpg', 'Monuments and just buildings - 2099090261_1efb89ae95_o.jpg image',  '2009-12-18', 2, 'BBK DP810', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 34);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (77, '1985-01-08', 'whoah ! wonderful',34, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (78, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',34, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (35, '2976358258_5c08813ddf_o.jpg', '2976358258_5c08813ddf_o.jpg', 'Monuments and just buildings - 2976358258_5c08813ddf_o.jpg image',  '2009-12-18', 2, 'BenQ DC E800', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 35);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (79, '1985-01-08', 'Fantastic job. Great light and color! Great shot!',35, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (36, '2[457x303].jpg', '2[457x303].jpg', 'Monuments and just buildings - 2[457x303].jpg image',  '2009-12-18', 2, 'Konica Minolta', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 36);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (80, '1985-01-08', 'Superb Shot and so beautiful Colors !!! ',36, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (81, '1985-01-08', 'Superb Shot and so beautiful Colors !!! ',36, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (82, '1985-01-08', 'Such a lovely colour azaga!',36, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (83, '1985-01-08', 'Such a lovely colour azaga!',36, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (37, '32971256_8bd6bf165d_o.jpg', '32971256_8bd6bf165d_o.jpg', 'Monuments and just buildings - 32971256_8bd6bf165d_o.jpg image',  '2009-12-18', 2, 'Panasonic', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 37);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (84, '1985-01-08', 'Wonderful.',37, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (85, '1985-01-08', 'Amazing shot..',37, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (86, '1985-01-08', 'Fantastic job. Great light and color! Great shot!',37, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (87, '1985-01-08', 'that is a beautiful flower with great colours ',37, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (38, '3392872753_e47f1c7078_o.jpg', '3392872753_e47f1c7078_o.jpg', 'Monuments and just buildings - 3392872753_e47f1c7078_o.jpg image',  '2009-12-18', 2, 'LG LDC-A310', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 38);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (88, '1985-01-08', 'nice shot =) ',38, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (89, '1985-01-08', 'Stunning capture! :-)',38, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (39, '3397511520_9839039136_o.jpg', '3397511520_9839039136_o.jpg', 'Monuments and just buildings - 3397511520_9839039136_o.jpg image',  '2009-12-18', 2, 'Canon EOS 450D', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 39);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (90, '1985-01-08', 'Gorgeous! Lovely color!',39, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (40, '3399540332_576df61129_o.jpg', '3399540332_576df61129_o.jpg', 'Monuments and just buildings - 3399540332_576df61129_o.jpg image',  '2009-12-18', 2, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 40);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (91, '1985-01-08', 'Wonderful.',40, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (92, '1985-01-08', 'Very *lovely*',40, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (41, '3403152285_3e72d1264a_o.jpg', '3403152285_3e72d1264a_o.jpg', 'Monuments and just buildings - 3403152285_3e72d1264a_o.jpg image',  '2009-12-18', 2, 'Canon PowerShot SX110 IS', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 41);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (93, '1985-01-08', '++Beautiful',41, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (42, '3413630679_d77f525413_o.jpg', '3413630679_d77f525413_o.jpg', 'Monuments and just buildings - 3413630679_d77f525413_o.jpg image',  '2009-12-18', 2, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 42);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (94, '1985-01-08', 'Amazing shot..',42, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (43, '3414974777_a4f44f69c7_o.jpg', '3414974777_a4f44f69c7_o.jpg', 'Monuments and just buildings - 3414974777_a4f44f69c7_o.jpg image',  '2009-12-18', 2, 'Sony Alpha DSLR-A350', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 43);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (95, '1985-01-08', 'love every thing about this picture, really beautiful... :))',43, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (44, '3415278215_968db2dbbf_o.jpg', '3415278215_968db2dbbf_o.jpg', 'Monuments and just buildings - 3415278215_968db2dbbf_o.jpg image',  '2009-12-18', 2, 'Canon Digital IXUS 80 IS (PowerShot SD1100 IS)', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 44);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (96, '1985-01-08', 'this is extremely Good:) Congratulations!',44, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (97, '1985-01-08', 'that is a beautiful flower with great colours ',44, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (98, '1985-01-08', 'fantastic shot !!!!!!',44, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (45, '3415840434_949aeabf08_o.jpg', '3415840434_949aeabf08_o.jpg', 'Monuments and just buildings - 3415840434_949aeabf08_o.jpg image',  '2009-12-18', 2, 'Pentax Optio E40', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 45);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (99, '1985-01-08', 'Superb Shot and so beautiful Colors !!! ',45, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (46, '370031898_84e952e0b0_o.jpg', '370031898_84e952e0b0_o.jpg', 'Monuments and just buildings - 370031898_84e952e0b0_o.jpg image',  '2009-12-18', 2, 'Olympus Stylus mju 1040', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 46);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (100, '1985-01-08', 'fantastic shot !!!!!!',46, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (101, '1985-01-08', 'this is extremely Good:) Congratulations!',46, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (47, '381889523_f276d45e9d_o.jpg', '381889523_f276d45e9d_o.jpg', 'Monuments and just buildings - 381889523_f276d45e9d_o.jpg image',  '2009-12-18', 2, 'BBK DP810', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 47);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (102, '1985-01-08', 'I Think this is Art!',47, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (48, '441026665_07699ebddd_o.jpg', '441026665_07699ebddd_o.jpg', 'Monuments and just buildings - 441026665_07699ebddd_o.jpg image',  '2009-12-18', 2, 'BenQ DC E800', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 48);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (103, '1985-01-08', 'Gorgeous! Lovely color!',48, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (49, '4[457x303].jpg', '4[457x303].jpg', 'Monuments and just buildings - 4[457x303].jpg image',  '2009-12-18', 2, 'Konica Minolta', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 49);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (104, '1985-01-08', 'Gorgeous! Lovely color!',49, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (50, '960942349_dcc90c0a61_o.jpg', '960942349_dcc90c0a61_o.jpg', 'Monuments and just buildings - 960942349_dcc90c0a61_o.jpg image',  '2009-12-18', 2, 'Panasonic', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(2, 50);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (105, '1985-01-08', 'Beautiful colours. Nice close up. ',50, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (106, '1985-01-08', 'love every thing about this picture, really beautiful... :))',50, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (107, '1985-01-08', 'Very *lovely*',50, 3);
UPDATE Album set coveringImage_id=50 where id = 2;

---------------------------------------------------------------------
-- ALBUM - Nature"
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (3, 'Nature', 'Nature pictures',  1, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (3,  'Nature');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (51, '01[303x202].jpg', '01[303x202].jpg', 'Nature - 01[303x202].jpg image',  '2009-12-18', 3, 'LG LDC-A310', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 51);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (108, '1985-01-08', 'Perfecft!',51, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (109, '1985-01-08', 'I Think this is Art!',51, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (110, '1985-01-08', 'that is a beautiful flower with great colours ',51, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (52, '1[305x457].jpg', '1[305x457].jpg', 'Nature - 1[305x457].jpg image',  '2009-12-18', 3, 'Canon EOS 450D', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 52);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (111, '1985-01-08', 'really pretty. it looks like there is a lady in the _center_, blowing kisses!!',52, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (53, '273927725_c9f5ef5952_o.jpg', '273927725_c9f5ef5952_o.jpg', 'Nature - 273927725_c9f5ef5952_o.jpg image',  '2009-12-18', 3, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 53);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (112, '1985-01-08', 'Very *lovely*',53, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (113, '1985-01-08', 'Very *lovely*',53, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (114, '1985-01-08', 'whoah ! wonderful',53, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (115, '1985-01-08', 'Bellissima macro!',53, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (54, '2[303x457].jpg', '2[303x457].jpg', 'Nature - 2[303x457].jpg image',  '2009-12-18', 3, 'Canon PowerShot SX110 IS', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 54);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (116, '1985-01-08', 'Beautiful ^Flower^...great Macro....Excellent !!!',54, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (117, '1985-01-08', 'I Think this is Art!',54, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (55, '3392730627_1cdb18cba6_o.jpg', '3392730627_1cdb18cba6_o.jpg', 'Nature - 3392730627_1cdb18cba6_o.jpg image',  '2009-12-18', 3, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 55);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (56, '3392993334_36d7f097df_o.jpg', '3392993334_36d7f097df_o.jpg', 'Nature - 3392993334_36d7f097df_o.jpg image',  '2009-12-18', 3, 'Sony Alpha DSLR-A350', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 56);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (118, '1985-01-08', 'Stunning capture! :-)',56, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (119, '1985-01-08', 'Wonderful.',56, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (120, '1985-01-08', 'love every thing about this picture, really beautiful... :))',56, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (57, '3393235094_7cc613d363_o.jpg', '3393235094_7cc613d363_o.jpg', 'Nature - 3393235094_7cc613d363_o.jpg image',  '2009-12-18', 3, 'Canon Digital IXUS 80 IS (PowerShot SD1100 IS)', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 57);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (121, '1985-01-08', 'Amazing shot..',57, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (122, '1985-01-08', 'Wow!! Macro stupenda!!! Complimenti! ',57, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (58, '3412793157_683290a536_o.jpg', '3412793157_683290a536_o.jpg', 'Nature - 3412793157_683290a536_o.jpg image',  '2009-12-18', 3, 'Pentax Optio E40', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 58);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (59, '3414996504_b42abb3be6_o.jpg', '3414996504_b42abb3be6_o.jpg', 'Nature - 3414996504_b42abb3be6_o.jpg image',  '2009-12-18', 3, 'Olympus Stylus mju 1040', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 59);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (60, '3415639261_20d2d3b52c_o.jpg', '3415639261_20d2d3b52c_o.jpg', 'Nature - 3415639261_20d2d3b52c_o.jpg image',  '2009-12-18', 3, 'BBK DP810', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 60);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (61, '3416307534_40f6616283_o.jpg', '3416307534_40f6616283_o.jpg', 'Nature - 3416307534_40f6616283_o.jpg image',  '2009-12-18', 3, 'BenQ DC E800', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 61);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (123, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',61, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (124, '1985-01-08', 'Wonderful.',61, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (62, '3416321407_068332d9f2_o.jpg', '3416321407_068332d9f2_o.jpg', 'Nature - 3416321407_068332d9f2_o.jpg image',  '2009-12-18', 3, 'Konica Minolta', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 62);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (125, '1985-01-08', 'Superb Shot and so beautiful Colors !!! ',62, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (126, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',62, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (63, '3416371116_f793561d19_o.jpg', '3416371116_f793561d19_o.jpg', 'Nature - 3416371116_f793561d19_o.jpg image',  '2009-12-18', 3, 'Panasonic', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 63);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (127, '1985-01-08', 'Stunning capture! :-)',63, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (128, '1985-01-08', 'this is extremely Good:) Congratulations!',63, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (64, '3417131300_2aac5ec32c_o.jpg', '3417131300_2aac5ec32c_o.jpg', 'Nature - 3417131300_2aac5ec32c_o.jpg image',  '2009-12-18', 3, 'LG LDC-A310', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 64);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (129, '1985-01-08', 'Beautiful colours. Nice close up. ',64, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (130, '1985-01-08', 'Beautiful ^Flower^...great Macro....Excellent !!!',64, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (131, '1985-01-08', 'I Think this is Art!',64, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (132, '1985-01-08', 'I Think this is Art!',64, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (65, '3417132360_56d0b48a9d_o.jpg', '3417132360_56d0b48a9d_o.jpg', 'Nature - 3417132360_56d0b48a9d_o.jpg image',  '2009-12-18', 3, 'Canon EOS 450D', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 65);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (133, '1985-01-08', 'love every thing about this picture, really beautiful... :))',65, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (134, '1985-01-08', 'Very *lovely*',65, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (135, '1985-01-08', 'Beautiful colours. Nice close up. ',65, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (136, '1985-01-08', 'Wow!! Macro stupenda!!! Complimenti! ',65, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (66, '3417319507_8d1f2d5c77_o.jpg', '3417319507_8d1f2d5c77_o.jpg', 'Nature - 3417319507_8d1f2d5c77_o.jpg image',  '2009-12-18', 3, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 66);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (137, '1985-01-08', 'I Think this is Art!',66, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (138, '1985-01-08', 'Bellissima macro!',66, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (139, '1985-01-08', '++Beautiful',66, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (67, '3417494725_e21724aba9_o.jpg', '3417494725_e21724aba9_o.jpg', 'Nature - 3417494725_e21724aba9_o.jpg image',  '2009-12-18', 3, 'Canon PowerShot SX110 IS', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 67);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (68, '3418371942_a61ae58de4_o.jpg', '3418371942_a61ae58de4_o.jpg', 'Nature - 3418371942_a61ae58de4_o.jpg image',  '2009-12-18', 3, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 68);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (140, '1985-01-08', 'Perfecft!',68, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (69, '345009210_1f826cd5a1_o.jpg', '345009210_1f826cd5a1_o.jpg', 'Nature - 345009210_1f826cd5a1_o.jpg image',  '2009-12-18', 3, 'Sony Alpha DSLR-A350', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 69);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (70, '55109059_55408b3d5f_o.jpg', '55109059_55408b3d5f_o.jpg', 'Nature - 55109059_55408b3d5f_o.jpg image',  '2009-12-18', 3, 'Canon Digital IXUS 80 IS (PowerShot SD1100 IS)', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 70);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (141, '1985-01-08', 'Beautiful ^Flower^...great Macro....Excellent !!!',70, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (142, '1985-01-08', 'Stunning capture! :-)',70, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (71, '570841724_b11c8b0df2_o.jpg', '570841724_b11c8b0df2_o.jpg', 'Nature - 570841724_b11c8b0df2_o.jpg image',  '2009-12-18', 3, 'Pentax Optio E40', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 71);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (143, '1985-01-08', 'Beautiful ^Flower^...great Macro....Excellent !!!',71, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (72, '672840660_4a91f39f02_o.jpg', '672840660_4a91f39f02_o.jpg', 'Nature - 672840660_4a91f39f02_o.jpg image',  '2009-12-18', 3, 'Olympus Stylus mju 1040', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(3, 72);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (144, '1985-01-08', 'this is extremely Good:) Congratulations!',72, 3);
UPDATE Album set coveringImage_id=72 where id = 3;

---------------------------------------------------------------------
-- ALBUM - Portrait"
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (4, 'Portrait', 'Portrait pictures',  3, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (4,  'Portrait');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (73, '02[303x202].jpg', '02[303x202].jpg', 'Portrait - 02[303x202].jpg image',  '2009-12-18', 4, 'BBK DP810', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(4, 73);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (145, '1985-01-08', 'Amazing shot..',73, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (146, '1985-01-08', 'whoah ! wonderful',73, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (147, '1985-01-08', 'fantastic shot !!!!!!',73, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (148, '1985-01-08', 'Bellissima macro!',73, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (74, '1033975999_7e058fcf1c_o.jpg', '1033975999_7e058fcf1c_o.jpg', 'Portrait - 1033975999_7e058fcf1c_o.jpg image',  '2009-12-18', 4, 'BenQ DC E800', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(4, 74);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (149, '1985-01-08', 'that is a beautiful flower with great colours ',74, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (150, '1985-01-08', 'love every thing about this picture, really beautiful... :))',74, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (151, '1985-01-08', 'love every thing about this picture, really beautiful... :))',74, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (75, '1516027705_ddff0a70dd_o.jpg', '1516027705_ddff0a70dd_o.jpg', 'Portrait - 1516027705_ddff0a70dd_o.jpg image',  '2009-12-18', 4, 'Konica Minolta', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(4, 75);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (152, '1985-01-08', 'Beautiful colours. Nice close up. ',75, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (153, '1985-01-08', 'that is a beautiful flower with great colours ',75, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (76, '1799039821_b6ee53167c_o.jpg', '1799039821_b6ee53167c_o.jpg', 'Portrait - 1799039821_b6ee53167c_o.jpg image',  '2009-12-18', 4, 'Panasonic', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(4, 76);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (154, '1985-01-08', 'this is extremely Good:) Congratulations!',76, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (155, '1985-01-08', 'Superb Shot and so beautiful Colors !!! ',76, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (77, '19654343_f445e1efa2_o.jpg', '19654343_f445e1efa2_o.jpg', 'Portrait - 19654343_f445e1efa2_o.jpg image',  '2009-12-18', 4, 'LG LDC-A310', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(4, 77);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (156, '1985-01-08', 'Wonderful.',77, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (157, '1985-01-08', 'Very *lovely*',77, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (78, '2187016030_e81b911b3e_o.jpg', '2187016030_e81b911b3e_o.jpg', 'Portrait - 2187016030_e81b911b3e_o.jpg image',  '2009-12-18', 4, 'Canon EOS 450D', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(4, 78);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (158, '1985-01-08', 'Perfecft!',78, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (159, '1985-01-08', 'I Think this is Art!',78, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (79, '2307649188_a6206c17a4_o.jpg', '2307649188_a6206c17a4_o.jpg', 'Portrait - 2307649188_a6206c17a4_o.jpg image',  '2009-12-18', 4, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(4, 79);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (80, '2879155528_1ebbaaf951_o.jpg', '2879155528_1ebbaaf951_o.jpg', 'Portrait - 2879155528_1ebbaaf951_o.jpg image',  '2009-12-18', 4, 'Canon PowerShot SX110 IS', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(4, 80);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (81, '2933904745_b323535d4a_o.jpg', '2933904745_b323535d4a_o.jpg', 'Portrait - 2933904745_b323535d4a_o.jpg image',  '2009-12-18', 4, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(4, 81);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (160, '1985-01-08', 'Such a lovely colour azaga!',81, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (161, '1985-01-08', 'whoah ! wonderful',81, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (162, '1985-01-08', 'Perfecft!',81, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (163, '1985-01-08', 'Wow!! Macro stupenda!!! Complimenti! ',81, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (82, '3-3.jpg', '3-3.jpg', 'Portrait - 3-3.jpg image',  '2009-12-18', 4, 'Sony Alpha DSLR-A350', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(4, 82);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (164, '1985-01-08', 'Bell??sima.!!! saludos.',82, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (165, '1985-01-08', 'Stunning capture! :-)',82, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (166, '1985-01-08', 'whoah ! wonderful',82, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (83, '3176975826_ef29a459fa_o.jpg', '3176975826_ef29a459fa_o.jpg', 'Portrait - 3176975826_ef29a459fa_o.jpg image',  '2009-12-18', 4, 'Canon Digital IXUS 80 IS (PowerShot SD1100 IS)', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(4, 83);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (167, '1985-01-08', 'Amazing shot..',83, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (84, '323301225_3aef765e5a_o.jpg', '323301225_3aef765e5a_o.jpg', 'Portrait - 323301225_3aef765e5a_o.jpg image',  '2009-12-18', 4, 'Pentax Optio E40', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(4, 84);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (168, '1985-01-08', 'Gorgeous! Lovely color!',84, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (169, '1985-01-08', 'Superb Shot and so beautiful Colors !!! ',84, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (170, '1985-01-08', 'Such a lovely colour azaga!',84, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (85, 'img_0103.jpg', 'img_0103.jpg', 'Portrait - img_0103.jpg image',  '2009-12-18', 4, 'Olympus Stylus mju 1040', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(4, 85);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (86, '_mg_0161.jpg', '_mg_0161.jpg', 'Portrait - _mg_0161.jpg image',  '2009-12-18', 4, 'BBK DP810', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(4, 86);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (171, '1985-01-08', 'Very *lovely*',86, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (172, '1985-01-08', 'Very *lovely*',86, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (173, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',86, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (174, '1985-01-08', 'Perfecft!',86, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (87, '_mg_0264.jpg', '_mg_0264.jpg', 'Portrait - _mg_0264.jpg image',  '2009-12-18', 4, 'BenQ DC E800', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(4, 87);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (88, '_mg_0510.jpg', '_mg_0510.jpg', 'Portrait - _mg_0510.jpg image',  '2009-12-18', 4, 'Konica Minolta', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(4, 88);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (175, '1985-01-08', 'this is extremely Good:) Congratulations!',88, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (176, '1985-01-08', 'Bellissima macro!',88, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (177, '1985-01-08', 'that is a beautiful flower with great colours ',88, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (178, '1985-01-08', 'Stunning capture! :-)',88, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (89, '_mg_1788.jpg', '_mg_1788.jpg', 'Portrait - _mg_1788.jpg image',  '2009-12-18', 4, 'Panasonic', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(4, 89);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (179, '1985-01-08', 'that is a beautiful flower with great colours ',89, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (180, '1985-01-08', 'that is a beautiful flower with great colours ',89, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (181, '1985-01-08', 'really pretty. it looks like there is a lady in the _center_, blowing kisses!!',89, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (182, '1985-01-08', 'Wonderful.',89, 1);
UPDATE Album set coveringImage_id=89 where id = 4;

---------------------------------------------------------------------
-- ALBUM - Sport"
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (5, 'Sport', 'Sport pictures',  2, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (5,  'Sport');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (90, '103193233_860c47c909_o.jpg', '103193233_860c47c909_o.jpg', 'Sport - 103193233_860c47c909_o.jpg image',  '2009-12-18', 5, 'LG LDC-A310', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(5, 90);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (183, '1985-01-08', 'love every thing about this picture, really beautiful... :))',90, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (184, '1985-01-08', 'fantastic shot !!!!!!',90, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (185, '1985-01-08', 'that is a beautiful flower with great colours ',90, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (186, '1985-01-08', 'Beautiful colours. Nice close up. ',90, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (91, '1350250361_2d963dd4e7_o.jpg', '1350250361_2d963dd4e7_o.jpg', 'Sport - 1350250361_2d963dd4e7_o.jpg image',  '2009-12-18', 5, 'Canon EOS 450D', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(5, 91);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (92, '2042654579_d25c0db64f_o.jpg', '2042654579_d25c0db64f_o.jpg', 'Sport - 2042654579_d25c0db64f_o.jpg image',  '2009-12-18', 5, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(5, 92);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (187, '1985-01-08', 'Bell??sima.!!! saludos.',92, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (188, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',92, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (189, '1985-01-08', 'Superb Shot and so beautiful Colors !!! ',92, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (190, '1985-01-08', 'whoah ! wonderful',92, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (93, '2158910844_e5a6deeeab_o.jpg', '2158910844_e5a6deeeab_o.jpg', 'Sport - 2158910844_e5a6deeeab_o.jpg image',  '2009-12-18', 5, 'Canon PowerShot SX110 IS', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(5, 93);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (191, '1985-01-08', 'that is a beautiful flower with great colours ',93, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (192, '1985-01-08', 'I Think this is Art!',93, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (193, '1985-01-08', 'nice shot =) ',93, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (194, '1985-01-08', 'love every thing about this picture, really beautiful... :))',93, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (94, '2228156682_74520fa2e5_o.jpg', '2228156682_74520fa2e5_o.jpg', 'Sport - 2228156682_74520fa2e5_o.jpg image',  '2009-12-18', 5, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(5, 94);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (195, '1985-01-08', 'Fantastic job. Great light and color! Great shot!',94, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (196, '1985-01-08', 'Wonderful.',94, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (197, '1985-01-08', 'Bellissima macro!',94, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (198, '1985-01-08', 'really pretty. it looks like there is a lady in the _center_, blowing kisses!!',94, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (95, '2264670815_82c5619ec6_o.jpg', '2264670815_82c5619ec6_o.jpg', 'Sport - 2264670815_82c5619ec6_o.jpg image',  '2009-12-18', 5, 'Sony Alpha DSLR-A350', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(5, 95);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (199, '1985-01-08', 'Fantastic job. Great light and color! Great shot!',95, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (200, '1985-01-08', 'I Think this is Art!',95, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (201, '1985-01-08', 'nice shot =) ',95, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (96, '2266019996_6b7fcb9d41_o.jpg', '2266019996_6b7fcb9d41_o.jpg', 'Sport - 2266019996_6b7fcb9d41_o.jpg image',  '2009-12-18', 5, 'Canon Digital IXUS 80 IS (PowerShot SD1100 IS)', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(5, 96);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (202, '1985-01-08', 'Fantastic job. Great light and color! Great shot!',96, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (203, '1985-01-08', 'Gorgeous! Lovely color!',96, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (204, '1985-01-08', '++Beautiful',96, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (205, '1985-01-08', 'Stunning capture! :-)',96, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (97, '2338319633_4feac41833_o.jpg', '2338319633_4feac41833_o.jpg', 'Sport - 2338319633_4feac41833_o.jpg image',  '2009-12-18', 5, 'Pentax Optio E40', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(5, 97);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (206, '1985-01-08', 'Wonderful.',97, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (207, '1985-01-08', 'Wonderful.',97, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (208, '1985-01-08', 'Stunning capture! :-)',97, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (98, '2541626690_d6c35f7122_o.jpg', '2541626690_d6c35f7122_o.jpg', 'Sport - 2541626690_d6c35f7122_o.jpg image',  '2009-12-18', 5, 'Olympus Stylus mju 1040', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(5, 98);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (209, '1985-01-08', 'that is a beautiful flower with great colours ',98, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (210, '1985-01-08', 'Wonderful.',98, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (211, '1985-01-08', 'Wow!! Macro stupenda!!! Complimenti! ',98, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (212, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',98, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (99, '2760610141_8a56e469d0_o.jpg', '2760610141_8a56e469d0_o.jpg', 'Sport - 2760610141_8a56e469d0_o.jpg image',  '2009-12-18', 5, 'BBK DP810', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(5, 99);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (213, '1985-01-08', 'Wow!! Macro stupenda!!! Complimenti! ',99, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (214, '1985-01-08', 'this is extremely Good:) Congratulations!',99, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (100, '2767537621_e378165405_o.jpg', '2767537621_e378165405_o.jpg', 'Sport - 2767537621_e378165405_o.jpg image',  '2009-12-18', 5, 'BenQ DC E800', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(5, 100);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (101, '2813129574_018acd119d_o.jpg', '2813129574_018acd119d_o.jpg', 'Sport - 2813129574_018acd119d_o.jpg image',  '2009-12-18', 5, 'Konica Minolta', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(5, 101);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (215, '1985-01-08', '++Beautiful',101, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (216, '1985-01-08', 'Perfecft!',101, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (102, '2832747707_fb68c8147f_o.jpg', '2832747707_fb68c8147f_o.jpg', 'Sport - 2832747707_fb68c8147f_o.jpg image',  '2009-12-18', 5, 'Panasonic', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(5, 102);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (217, '1985-01-08', 'love every thing about this picture, really beautiful... :))',102, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (103, '3037432738_e4b2388861_o.jpg', '3037432738_e4b2388861_o.jpg', 'Sport - 3037432738_e4b2388861_o.jpg image',  '2009-12-18', 5, 'LG LDC-A310', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(5, 103);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (218, '1985-01-08', 'Beautiful ^Flower^...great Macro....Excellent !!!',103, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (219, '1985-01-08', 'whoah ! wonderful',103, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (220, '1985-01-08', 'whoah ! wonderful',103, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (221, '1985-01-08', 'Such a lovely colour azaga!',103, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (104, '413103429_50ff9d8ae3_o.jpg', '413103429_50ff9d8ae3_o.jpg', 'Sport - 413103429_50ff9d8ae3_o.jpg image',  '2009-12-18', 5, 'Canon EOS 450D', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(5, 104);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (222, '1985-01-08', 'Gorgeous! Lovely color!',104, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (223, '1985-01-08', 'love every thing about this picture, really beautiful... :))',104, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (224, '1985-01-08', 'really pretty. it looks like there is a lady in the _center_, blowing kisses!!',104, 1);
UPDATE Album set coveringImage_id=104 where id = 5;

---------------------------------------------------------------------
-- ALBUM - Water"
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (6, 'Water', 'Water pictures',  5, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (6,  'Water');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (105, '117215467_5cccef9aaa_b.jpg', '117215467_5cccef9aaa_b.jpg', 'Water - 117215467_5cccef9aaa_b.jpg image',  '2009-12-18', 6, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(6, 105);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (106, '1323769314_fe850cd954_o.jpg', '1323769314_fe850cd954_o.jpg', 'Water - 1323769314_fe850cd954_o.jpg image',  '2009-12-18', 6, 'Canon PowerShot SX110 IS', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(6, 106);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (225, '1985-01-08', 'Such a lovely colour azaga!',106, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (226, '1985-01-08', 'Wonderful.',106, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (107, '205579493_baf0f850d1_o.jpg', '205579493_baf0f850d1_o.jpg', 'Water - 205579493_baf0f850d1_o.jpg image',  '2009-12-18', 6, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(6, 107);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (227, '1985-01-08', 'Wow!! Macro stupenda!!! Complimenti! ',107, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (228, '1985-01-08', 'Superb Shot and so beautiful Colors !!! ',107, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (229, '1985-01-08', 'whoah ! wonderful',107, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (108, '2198502835_1644c8fde2_o.jpg', '2198502835_1644c8fde2_o.jpg', 'Water - 2198502835_1644c8fde2_o.jpg image',  '2009-12-18', 6, 'Sony Alpha DSLR-A350', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(6, 108);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (230, '1985-01-08', 'Bell??sima.!!! saludos.',108, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (231, '1985-01-08', 'this is extremely Good:) Congratulations!',108, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (232, '1985-01-08', 'Stunning capture! :-)',108, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (233, '1985-01-08', 'Bellissima macro!',108, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (109, '2242254221_f2af58e243_o.jpg', '2242254221_f2af58e243_o.jpg', 'Water - 2242254221_f2af58e243_o.jpg image',  '2009-12-18', 6, 'Canon Digital IXUS 80 IS (PowerShot SD1100 IS)', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(6, 109);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (234, '1985-01-08', 'Superb Shot and so beautiful Colors !!! ',109, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (235, '1985-01-08', 'Perfecft!',109, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (236, '1985-01-08', 'Beautiful colours. Nice close up. ',109, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (110, '3170219697_4d259ff802_o.jpg', '3170219697_4d259ff802_o.jpg', 'Water - 3170219697_4d259ff802_o.jpg image',  '2009-12-18', 6, 'Pentax Optio E40', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(6, 110);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (237, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',110, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (238, '1985-01-08', 'Beautiful ^Flower^...great Macro....Excellent !!!',110, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (111, '3200123361_cfd16bb12a_o.jpg', '3200123361_cfd16bb12a_o.jpg', 'Water - 3200123361_cfd16bb12a_o.jpg image',  '2009-12-18', 6, 'Olympus Stylus mju 1040', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(6, 111);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (239, '1985-01-08', 'Beautiful ^Flower^...great Macro....Excellent !!!',111, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (240, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',111, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (112, '3253676430_b14ae3f5c9_o.jpg', '3253676430_b14ae3f5c9_o.jpg', 'Water - 3253676430_b14ae3f5c9_o.jpg image',  '2009-12-18', 6, 'BBK DP810', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(6, 112);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (113, '336589255_6a8ba0ebb6_o.jpg', '336589255_6a8ba0ebb6_o.jpg', 'Water - 336589255_6a8ba0ebb6_o.jpg image',  '2009-12-18', 6, 'BenQ DC E800', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(6, 113);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (241, '1985-01-08', 'Amazing shot..',113, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (242, '1985-01-08', 'Gorgeous! Lovely color!',113, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (243, '1985-01-08', 'Wonderful.',113, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (244, '1985-01-08', 'Stunning capture! :-)',113, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (114, '3411135019_0cc403fcb8_o.jpg', '3411135019_0cc403fcb8_o.jpg', 'Water - 3411135019_0cc403fcb8_o.jpg image',  '2009-12-18', 6, 'Konica Minolta', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(6, 114);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (115, '3416553183_2564d26ef1_b.jpg', '3416553183_2564d26ef1_b.jpg', 'Water - 3416553183_2564d26ef1_b.jpg image',  '2009-12-18', 6, 'Panasonic', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(6, 115);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (245, '1985-01-08', 'Bellissima macro!',115, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (246, '1985-01-08', 'Perfecft!',115, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (247, '1985-01-08', 'Bellissima macro!',115, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (116, '381634787_f52e84a5af_o.jpg', '381634787_f52e84a5af_o.jpg', 'Water - 381634787_f52e84a5af_o.jpg image',  '2009-12-18', 6, 'LG LDC-A310', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(6, 116);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (248, '1985-01-08', 'really pretty. it looks like there is a lady in the _center_, blowing kisses!!',116, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (249, '1985-01-08', 'Amazing shot..',116, 1);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (250, '1985-01-08', 'Amazing shot..',116, 3);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (117, '396318116_a47c4a8392_o.jpg', '396318116_a47c4a8392_o.jpg', 'Water - 396318116_a47c4a8392_o.jpg image',  '2009-12-18', 6, 'Canon EOS 450D', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(6, 117);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (251, '1985-01-08', 'Bell??sima.!!! saludos.',117, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (252, '1985-01-08', 'Perfecft!',117, 2);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (118, '412426470_47be44c1f1_o.jpg', '412426470_47be44c1f1_o.jpg', 'Water - 412426470_47be44c1f1_o.jpg image',  '2009-12-18', 6, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(6, 118);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (253, '1985-01-08', 'Beautiful ^Flower^...great Macro....Excellent !!!',118, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (254, '1985-01-08', 'Very *lovely*',118, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (119, '7530427_08dcad5953_o.jpg', '7530427_08dcad5953_o.jpg', 'Water - 7530427_08dcad5953_o.jpg image',  '2009-12-18', 6, 'Canon PowerShot SX110 IS', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(6, 119);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (120, '767963055_bd02f406d6_o.jpg', '767963055_bd02f406d6_o.jpg', 'Water - 767963055_bd02f406d6_o.jpg image',  '2009-12-18', 6, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(6, 120);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (255, '1985-01-08', 'Perfecft!',120, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (256, '1985-01-08', 'Gorgeous! Lovely color!',120, 1);
UPDATE Album set coveringImage_id=120 where id = 6;

---------------------------------------------------------------------
---------------------------------------------------------------------
-- TEST DATA
---------------------------------------------------------------------
---------------------------------------------------------------------

---------------------------------------------------------------------
-- ALBUM - MyShelf - 100
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (100, 'MyAlbum 100', 'MyAlbum pictures',  100, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (10006,  'MyAlbum_100');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (100027, '117215467_5cccef9aaa_b.jpg', '117215467_5cccef9aaa_b.jpg', 'Water - 117215467_5cccef9aaa_b.jpg image',  '2009-12-18', 100, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(10006, 100027);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (100052, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',100027, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (100053, '1985-01-08', 'love every thing about this picture, really beautiful... :))',100027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (100054, '1985-01-08', 'Perfecft!',100027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (100055, '1985-01-08', 'Beautiful colours. Nice close up. ',100027, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (100029, '205579493_baf0f850d1_o.jpg', '205579493_baf0f850d1_o.jpg', 'Water - 205579493_baf0f850d1_o.jpg image',  '2009-12-18', 100, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(10006, 100029);
UPDATE Album set coveringImage_id=100027 where id = 1000;
---------------------------------------------------------------------
-- ALBUM - MyShelf - 101
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (101, 'MyAlbum 101', 'MyAlbum pictures',  100, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (10106,  'MyAlbum_101');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (101027, '117215467_5cccef9aaa_b.jpg', '117215467_5cccef9aaa_b.jpg', 'Water - 117215467_5cccef9aaa_b.jpg image',  '2009-12-18', 101, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(10106, 101027);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (101052, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',101027, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (101053, '1985-01-08', 'love every thing about this picture, really beautiful... :))',101027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (101054, '1985-01-08', 'Perfecft!',101027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (101055, '1985-01-08', 'Beautiful colours. Nice close up. ',101027, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (101029, '205579493_baf0f850d1_o.jpg', '205579493_baf0f850d1_o.jpg', 'Water - 205579493_baf0f850d1_o.jpg image',  '2009-12-18', 101, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(10106, 101029);
UPDATE Album set coveringImage_id=101027 where id = 1010;
---------------------------------------------------------------------
-- ALBUM - MyShelf - 102
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (102, 'MyAlbum 102', 'MyAlbum pictures',  101, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (10206,  'MyAlbum_102');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (102027, '117215467_5cccef9aaa_b.jpg', '117215467_5cccef9aaa_b.jpg', 'Water - 117215467_5cccef9aaa_b.jpg image',  '2009-12-18', 102, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(10206, 102027);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (102052, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',102027, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (102053, '1985-01-08', 'love every thing about this picture, really beautiful... :))',102027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (102054, '1985-01-08', 'Perfecft!',102027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (102055, '1985-01-08', 'Beautiful colours. Nice close up. ',102027, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (102029, '205579493_baf0f850d1_o.jpg', '205579493_baf0f850d1_o.jpg', 'Water - 205579493_baf0f850d1_o.jpg image',  '2009-12-18', 102, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(10206, 102029);
UPDATE Album set coveringImage_id=102027 where id = 1020;
---------------------------------------------------------------------
-- ALBUM - MyShelf - 103
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (103, 'MyAlbum 103', 'MyAlbum pictures',  101, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (10306,  'MyAlbum_103');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (103027, '117215467_5cccef9aaa_b.jpg', '117215467_5cccef9aaa_b.jpg', 'Water - 117215467_5cccef9aaa_b.jpg image',  '2009-12-18', 103, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(10306, 103027);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (103052, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',103027, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (103053, '1985-01-08', 'love every thing about this picture, really beautiful... :))',103027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (103054, '1985-01-08', 'Perfecft!',103027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (103055, '1985-01-08', 'Beautiful colours. Nice close up. ',103027, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (103029, '205579493_baf0f850d1_o.jpg', '205579493_baf0f850d1_o.jpg', 'Water - 205579493_baf0f850d1_o.jpg image',  '2009-12-18', 103, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(10306, 103029);
UPDATE Album set coveringImage_id=103027 where id = 1030;

---------------------------------------------------------------------
-- ALBUM - MyShelf - 110
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (110, 'MyAlbum 110', 'MyAlbum pictures',  110, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (11006,  'MyAlbum_110');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (110027, '117215467_5cccef9aaa_b.jpg', '117215467_5cccef9aaa_b.jpg', 'Water - 117215467_5cccef9aaa_b.jpg image',  '2009-12-18', 110, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(11006, 110027);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (110052, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',110027, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (110053, '1985-01-08', 'love every thing about this picture, really beautiful... :))',110027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (110054, '1985-01-08', 'Perfecft!',110027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (110055, '1985-01-08', 'Beautiful colours. Nice close up. ',110027, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (110029, '205579493_baf0f850d1_o.jpg', '205579493_baf0f850d1_o.jpg', 'Water - 205579493_baf0f850d1_o.jpg image',  '2009-12-18', 110, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(11006, 110029);
UPDATE Album set coveringImage_id=110027 where id = 1100;
---------------------------------------------------------------------
-- ALBUM - MyShelf - 111
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (111, 'MyAlbum 111', 'MyAlbum pictures',  110, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (11106,  'MyAlbum_111');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (111027, '117215467_5cccef9aaa_b.jpg', '117215467_5cccef9aaa_b.jpg', 'Water - 117215467_5cccef9aaa_b.jpg image',  '2009-12-18', 111, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(11106, 111027);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (111052, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',111027, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (111053, '1985-01-08', 'love every thing about this picture, really beautiful... :))',111027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (111054, '1985-01-08', 'Perfecft!',111027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (111055, '1985-01-08', 'Beautiful colours. Nice close up. ',111027, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (111029, '205579493_baf0f850d1_o.jpg', '205579493_baf0f850d1_o.jpg', 'Water - 205579493_baf0f850d1_o.jpg image',  '2009-12-18', 111, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(11106, 111029);
UPDATE Album set coveringImage_id=111027 where id = 1110;
---------------------------------------------------------------------
-- ALBUM - MyShelf - 112
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (112, 'MyAlbum 112', 'MyAlbum pictures',  111, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (11206,  'MyAlbum_112');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (112027, '117215467_5cccef9aaa_b.jpg', '117215467_5cccef9aaa_b.jpg', 'Water - 117215467_5cccef9aaa_b.jpg image',  '2009-12-18', 112, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(11206, 112027);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (112052, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',112027, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (112053, '1985-01-08', 'love every thing about this picture, really beautiful... :))',112027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (112054, '1985-01-08', 'Perfecft!',112027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (112055, '1985-01-08', 'Beautiful colours. Nice close up. ',112027, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (112029, '205579493_baf0f850d1_o.jpg', '205579493_baf0f850d1_o.jpg', 'Water - 205579493_baf0f850d1_o.jpg image',  '2009-12-18', 112, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(11206, 112029);
UPDATE Album set coveringImage_id=112027 where id = 1120;
---------------------------------------------------------------------
-- ALBUM - MyShelf - 113
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (113, 'MyAlbum 113', 'MyAlbum pictures',  111, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (11306,  'MyAlbum_113');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (113027, '117215467_5cccef9aaa_b.jpg', '117215467_5cccef9aaa_b.jpg', 'Water - 117215467_5cccef9aaa_b.jpg image',  '2009-12-18', 113, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(11306, 113027);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (113052, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',113027, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (113053, '1985-01-08', 'love every thing about this picture, really beautiful... :))',113027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (113054, '1985-01-08', 'Perfecft!',113027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (113055, '1985-01-08', 'Beautiful colours. Nice close up. ',113027, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (113029, '205579493_baf0f850d1_o.jpg', '205579493_baf0f850d1_o.jpg', 'Water - 205579493_baf0f850d1_o.jpg image',  '2009-12-18', 113, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(11306, 113029);
UPDATE Album set coveringImage_id=113027 where id = 1130;

---------------------------------------------------------------------
-- ALBUM - MyShelf - 120
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (120, 'MyAlbum 120', 'MyAlbum pictures',  120, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (12006,  'MyAlbum_120');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (120027, '117215467_5cccef9aaa_b.jpg', '117215467_5cccef9aaa_b.jpg', 'Water - 117215467_5cccef9aaa_b.jpg image',  '2009-12-18', 120, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(12006, 120027);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (120052, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',120027, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (120053, '1985-01-08', 'love every thing about this picture, really beautiful... :))',120027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (120054, '1985-01-08', 'Perfecft!',120027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (120055, '1985-01-08', 'Beautiful colours. Nice close up. ',120027, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (120029, '205579493_baf0f850d1_o.jpg', '205579493_baf0f850d1_o.jpg', 'Water - 205579493_baf0f850d1_o.jpg image',  '2009-12-18', 120, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(12006, 120029);
UPDATE Album set coveringImage_id=120027 where id = 1200;
---------------------------------------------------------------------
-- ALBUM - MyShelf - 121
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (121, 'MyAlbum 121', 'MyAlbum pictures',  120, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (12106,  'MyAlbum_121');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (121027, '117215467_5cccef9aaa_b.jpg', '117215467_5cccef9aaa_b.jpg', 'Water - 117215467_5cccef9aaa_b.jpg image',  '2009-12-18', 121, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(12106, 121027);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (121052, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',121027, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (121053, '1985-01-08', 'love every thing about this picture, really beautiful... :))',121027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (121054, '1985-01-08', 'Perfecft!',121027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (121055, '1985-01-08', 'Beautiful colours. Nice close up. ',121027, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (121029, '205579493_baf0f850d1_o.jpg', '205579493_baf0f850d1_o.jpg', 'Water - 205579493_baf0f850d1_o.jpg image',  '2009-12-18', 121, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(12106, 121029);
UPDATE Album set coveringImage_id=121027 where id = 1210;
---------------------------------------------------------------------
-- ALBUM - MyShelf - 122
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (122, 'MyAlbum 122', 'MyAlbum pictures',  121, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (12206,  'MyAlbum_122');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (122027, '117215467_5cccef9aaa_b.jpg', '117215467_5cccef9aaa_b.jpg', 'Water - 117215467_5cccef9aaa_b.jpg image',  '2009-12-18', 122, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(12206, 122027);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (122052, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',122027, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (122053, '1985-01-08', 'love every thing about this picture, really beautiful... :))',122027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (122054, '1985-01-08', 'Perfecft!',122027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (122055, '1985-01-08', 'Beautiful colours. Nice close up. ',122027, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (122029, '205579493_baf0f850d1_o.jpg', '205579493_baf0f850d1_o.jpg', 'Water - 205579493_baf0f850d1_o.jpg image',  '2009-12-18', 122, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(12206, 122029);
UPDATE Album set coveringImage_id=122027 where id = 1220;
---------------------------------------------------------------------
-- ALBUM - MyShelf - 123
---------------------------------------------------------------------
INSERT INTO Album(id, name, description, shelf_id, created) VALUES (123, 'MyAlbum 123', 'MyAlbum pictures',  121, '2009-12-18');
INSERT INTO MetaTag(id, tag) VALUES (12306,  'MyAlbum_123');

INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (123027, '117215467_5cccef9aaa_b.jpg', '117215467_5cccef9aaa_b.jpg', 'Water - 117215467_5cccef9aaa_b.jpg image',  '2009-12-18', 123, 'Sony CyberShot DSC-T77', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(12306, 123027);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (123052, '1985-01-08', '|Wonderful| coloured flower .... *excellent* macro .... -nice- details!!!',123027, 2);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (123053, '1985-01-08', 'love every thing about this picture, really beautiful... :))',123027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (123054, '1985-01-08', 'Perfecft!',123027, 3);
  INSERT INTO Comment(id, date, message, image_id, author_id) VALUES (123055, '1985-01-08', 'Beautiful colours. Nice close up. ',123027, 1);
INSERT INTO Image(id, name, path, description, created, album_id, cameraModel, width, size, height, uploaded, allowComments, showMetaInfo) VALUES (123029, '205579493_baf0f850d1_o.jpg', '205579493_baf0f850d1_o.jpg', 'Water - 205579493_baf0f850d1_o.jpg image',  '2009-12-18', 123, 'Nikon D60', 1024, 1917, 768, '2009-12-01', true, true);
  INSERT INTO Image_MetaTag(IMAGETAGS_ID, IMAGES_ID) VALUES(12306, 123029);
UPDATE Album set coveringImage_id=123027 where id = 1230;


