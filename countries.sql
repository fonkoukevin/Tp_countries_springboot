
-- Database Server: postgreSQL
-- Database Name: countries



-- DROP TABLE IF EXISTS `countries`;
CREATE TABLE "countries" (
  "id" serial NOT NULL,
  "capital" varchar(255) DEFAULT NULL,
  "name" varchar(255) DEFAULT NULL,
  PRIMARY KEY ("id")
);

--
-- Dumping data for table `countries`
--

INSERT INTO "countries" VALUES (1,'Belgrade','Serbia'),(2,'Sofia','Bulgaria'),(3,'Dublin','Ireland'),(4,'Sarajevo','Bosnia and Herzegovina'),(5,'Vaduz','Liechtenstein'),(6,'Kyiv','Ukraine'),(7,'Douglas','Isle of Man'),(8,'Skopje','North Macedonia'),(9,'Moscow','Russia'),(10,'Chișinău','Moldova'),(11,'Ljubljana','Slovenia'),(12,'London','United Kingdom'),(13,'Zagreb','Croatia'),(14,'Budapest','Hungary'),(15,'Pristina','Kosovo'),(16,'Berlin','Germany'),(17,'Copenhagen','Denmark'),(18,'Lisbon','Portugal'),(19,'Luxembourg','Luxembourg'),(20,'Longyearbyen','Svalbard and Jan Mayen'),(21,'Gibraltar','Gibraltar'),(22,'Minsk','Belarus'),(23,'Bern','Switzerland'),(24,'Riga','Latvia'),(25,'Madrid','Spain'),(26,'Vatican City','Vatican City'),(27,'Nicosia','Cyprus'),(28,'Oslo','Norway'),(29,'Tallinn','Estonia'),(30,'Stockholm','Sweden'),(31,'Tórshavn','Faroe Islands'),(32,'Bucharest','Romania'),(33,'Reykjavik','Iceland'),(34,'Andorra la Vella','Andorra'),(35,'City of San Marino','San Marino'),(36,'Warsaw','Poland'),(37,'Valletta','Malta'),(38,'Rome','Italy'),(39,'Monaco','Monaco'),(40,'Helsinki','Finland'),(41,'Athens','Greece'),(42,'Paris','France'),(43,'Saint Helier','Jersey'),(44,'Vienna','Austria'),(45,'Tirana','Albania'),(46,'Vilnius','Lithuania'),(47,'Mariehamn','Åland Islands'),(48,'Bratislava','Slovakia'),(49,'St. Peter Port','Guernsey'),(50,'Amsterdam','Netherlands'),(51,'Prague','Czechia'),(52,'Podgorica','Montenegro'),(53,'Brussels','Belgium');




-- Table structure for table `players`
CREATE TABLE "players" (
  "id" serial NOT NULL,
  "username" varchar(255) NOT NULL,
  PRIMARY KEY ("id")
);

-- Table structure for table `scores`
CREATE TABLE "scores" (
  "id" serial NOT NULL,
  "player_id" integer NOT NULL,
  "score" integer NOT NULL DEFAULT 0,
  "correct_answers" integer NOT NULL DEFAULT 0,
  PRIMARY KEY ("id"),
  FOREIGN KEY ("player_id") REFERENCES "players"("id")
);
