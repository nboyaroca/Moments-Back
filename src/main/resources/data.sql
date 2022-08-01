
INSERT
INTO
  users
  (user_name, avatar)
VALUES
  ('Traveller', 'https://www.blog.motifphotos.com/wp-content/uploads/2018/12/iStock-902506410_1200x800px-1200x800.jpg');
INSERT
INTO
  users
  (user_name, avatar)
VALUES
  ('James Bond', 'https://www.dighttps://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/bond-gun-barrel-1601542498.jpg');


INSERT INTO
  moments
  (title, description, img_Url, publisher_id)
VALUES
  ('London', 'UK capital', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/ab/Big_ben_london_eye.jpg/768px-Big_ben_london_eye.jpg', 2);
INSERT
INTO
  moments
  (title, description, img_Url, publisher_id)
VALUES
  ('Rome', 'Italy capital', 'https://i.pinimg.com/originals/a6/ca/cc/a6cacc8ae54945f60564d53558f9d796.jpg', 1);
INSERT
INTO
  moments
  (title, description, img_Url, publisher_id)
VALUES
  ('New York', 'Most important city in the world', 'https://cdn.viewing.nyc/assets/media/fc275b1bd690c913a46b6250df72f481/elements/7dc8bc8fbf8d7347e6d97cfb6f4d8922/02feb6fa-326f-4082-bf52-a56e564d8941.jpg', 1);
INSERT
INTO
  moments
  (title, description, img_Url, publisher_id)
VALUES
  ('Tokyo', 'Japan''s capital', 'https://i.pinimg.com/originals/18/90/51/1890510f3aedb106fb0da13172403cc6.jpg', 1);
INSERT
INTO
  moments
  (title, description, img_Url, publisher_id)
VALUES
  ('Sydney', 'Important city in Australia', 'https://i.pinimg.com/474x/20/ae/cc/20aecc98f3829c2e27e0f04c96457158.jpg', 1);
INSERT
INTO
  moments
  (title, description, img_Url, publisher_id)
VALUES
  ('Paris', 'France capital', 'https://www.kevinandamanda.com/wp-content/uploads/2015/11/best-neighborhoods-to-explore-paris-france-walking-tours-01-480x720.jpg', 1);
INSERT
INTO
  moments
  (title, description, img_Url, publisher_id)
VALUES
  ('Moscow', 'Russia''s capital', 'http://www.robertharding.com/watermark.php?type=preview&im=RM/RH/VERTICAL/817-421385', 1);
INSERT
INTO
  moments
  (title, description, img_Url, publisher_id)
VALUES
  ('Athens', 'Greece''s capital', 'https://thumbs.dreamstime.com/b/acr%C3%B3polis-de-parten%C3%B3n-atenas-grecia-athens-greece-disparo-vertical-182186976.jpg', 1);


INSERT
INTO
  comments
  (comment, moment_id, publisher_id)
VALUES
  ('City of LOVE', 6, 1);
INSERT
INTO
  comments
  (comment, moment_id, publisher_id)
VALUES
  ('Athens is considered Democracy''s birthplace', 8, 1);
INSERT
INTO
  comments
  (comment, moment_id, publisher_id)
VALUES
  ('The gods chose the best site for their Olympus', 8, 1);
INSERT
INTO
  comments
  (comment, moment_id, publisher_id)
VALUES
  ('God save the queen, officially used in Commonwealth Countries', 1, 1);


  INSERT
INTO
  likes
  (liker_id, moment_id)
VALUES
  (1, 1);
INSERT
INTO
  likes
  (liker_id, moment_id)
VALUES
  (1, 8);
