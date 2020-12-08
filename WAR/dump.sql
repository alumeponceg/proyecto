CREATE DATABASE  IF NOT EXISTS `treatmenttracer` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `treatmenttracer`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: treatmenttracer
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `disease`
--

DROP TABLE IF EXISTS `disease`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `disease` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `causes` longtext,
  `description` longtext,
  `image` longtext,
  `name` varchar(45) DEFAULT NULL,
  `symptom` longtext,
  `state` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_am27lok2q3vo8p5cfc482b32u` (`name`),
  KEY `FKteo292p9s1k2dogffklyrparf` (`state`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disease`
--

LOCK TABLES `disease` WRITE;
/*!40000 ALTER TABLE `disease` DISABLE KEYS */;
INSERT INTO `disease` VALUES (1,'<p>Los principales afectados por la enfermedad son las personas expuestas a los dos factores externos que contribuyen en mayor medida a su desarrollo: fumar y trabajar en ambientes contaminados. </p><p>Por eso la enfermedad incide especialmente entre los mineros de carbón, los que trabajan con cereales, en la metalurgia y otros trabajadores expuestos al polvo.\r\n</p><p>\r\nLa enfermedad es más frecuente entre los hombres que entre las mujeres y tiene mayor mortalidad entre los varones. La mortalidad entre las personas que padecen la enfermedad es también mayor en pacientes de raza blanca y obrera, que entre los trabajadores administrativos.</p>','La enfermedad pulmonar obstructiva crónica, conocida por las siglas EPOC, en una enfermedad crónica de las vías áreas que llega a resultar incapacitante y tiene un gran impacto en la calidad de vida de las personas que la sufren.\r\n\r\nEsta enfermedad, que en la actualidad no tiene cura, consiste en una inflamación crónica de la mucosa bronquial que hace que se produzca un moco, que es anómalo, lo que lleva a una obstrucción de las vías aéreas.','https://firebasestorage.googleapis.com/v0/b/treatmenttracer.appspot.com/o/images%2FEPOC?alt=media&token=838127cd-de5b-4f60-a58a-1444987a3a8c','EPOC','<ul><li>Tos y aumento de la mucosidad, normalmente al levantarse por la mañana.\r\n</li><li>  \r\n Tendencia a sufrir resfriados de pecho.\r\n</li><li>  \r\n El esputo que se produce durante estos resfriados con frecuencia se vuelve amarillo o verde.\r\n</li><li>  \r\n A medida que pasan los años, estos catarros de pecho se vuelven más frecuentes.\r\n</li><li>  \r\n Respiración sibilante.\r\n</li><li>  \r\n Sensación de ahogo cuando se hace un esfuerzo y, más adelante, ahogo en actividades diarias.\r\n</li><li>  Hinchazón en las piernas, debida a la insuficiencia cardiaca.</li></ul>',1),(2,'<ul><li>No se conoce la causa exacta de la diabetes, entre otras cosas porque hay muchos tipos diferentes De hecho, el momento de aparición de la enfermedad, las causas y los síntomas que presentan los pacientes dependen del tipo de diabetes</li></ul>','La diabetes es una enfermedad crónica que se origina porque el páncreas no sintetiza la cantidad de insulina que el cuerpo humano necesita, la elabora de una calidad inferior o no es capaz de utilizarla con eficacia.\r\nLa insulina es una hormona producida por el páncreas. Su principal función es el mantenimiento de los valores adecuados de glucosa en sangre. \r\nEn las personas con diabetes hay un exceso de glucosa en sangre (hiperglucemia), ya que no se distribuye de la forma adecuada.','https://firebasestorage.googleapis.com/v0/b/treatmenttracer.appspot.com/o/images%2FDiabetes?alt=media&token=9b7c8779-0ae6-4804-bdc6-ba4789e59e64','Diabetes','<ul><li>Aumento de la sed</li><li> ganas frecuentes de orinar</li><li>hambre extrema</li><li>pérdida de peso inexplicable</li><li>presencia de cetonas en la orina <i>(las cetonas son un subproducto de la degradación muscular y de grasas que se produce cuando no hay insulina suficiente disponible)</i></li><li> fatiga</li><li> irritabilidad</li><li>visión borrosa.</li></ul>',1),(11,'<p>A día de hoy no se conoce la causa última de la EP. Sin embargo, se considera que podría deberse a una combinación de factores genéticos, medioambientales y los derivados del propio envejecimiento del organismo.</p><p>La edad es un claro factor de riesgo, lo más común es que la enfermedad inicie entre los 50-60 años.&nbsp;</p><p>El 90% de los casos de párkinson son formas esporádicas, es decir, no se deben a una alteración genética concreta. No obstante, se estima que entre el 15% y el 25% de las personas que tienen la enfermedad cuentan con algún pariente que la ha desarrollado.</p><p>Algunos estudios citan como factor de riesgo el consumo continuado a lo largo de los años de agua de pozo o haber estado expuesto a pesticidas y herbicidas.<br></p>','<p>La enfermedad de Parkinson (EP) es un trastorno neurodegenerativo que afecta al sistema nervioso de manera crónica y progresiva. Es la segunda enfermedad más prevalente en la actualidad después del Alzhéimer y pertenece a los llamados Trastornos del Movimiento.</p><p>Se conoce comúnmente como enfermedad de Parkinson en referencia a James Parkinson, el doctor que la describió por primera vez en 1817 en su monografía Un ensayo sobre la parálisis agitante (An essay on the shaking palsy).</p>','https://firebasestorage.googleapis.com/v0/b/treatmenttracer.appspot.com/o/images%2FParkinson?alt=media&token=6b8d145e-bbe9-4a37-8e75-da78bcee4914','Parkinson','<p>La EP se presenta a través de una serie de síntomas motores y otros no motores.</p><ul><li>Temblor en reposo&nbsp;</li><li><span style=\"font-size: 1rem;\">Rigidez&nbsp;</span></li><li><span style=\"font-size: 1rem;\">Bradicinesia&nbsp;</span></li><li><span style=\"font-size: 1rem;\">Inestabilidad postural</span></li><li><span style=\"font-size: 1rem;\">Hipofonía</span></li><li><span style=\"font-size: 1rem;\">Sialorrea</span></li><li><span style=\"font-size: 1rem;\">Dificultad respiratoria</span></li></ul>',1),(10,'<p>La causa oficial de la enfermedad de Alzheimer actualmente no se conoce, y aunque existen diversos estudios que asocian esta enfermedad a los priones, no hay ninguno que pueda afirmar a ciencia exacta que esa sea la causa de la enfermedad.</p>','<p>El Alzheimer es la forma más común de demencia, un término general que se aplica a la pérdida de memoria y otras habilidades cognitivas que interfieren con la vida cotidiana.</p><p>Es responsable de entre un 60 y un 80 por ciento de los casos de demencia. El Alzheimer no es una característica normal del envejecimiento. El factor de riesgo conocido más importante es el aumento de la edad, y la mayoría de las personas con Alzheimer son mayores de 65 años. Pero el Alzheimer no es solo una enfermedad de la vejez.<br></p>','','Alzheimer','<ul style=\"margin-right: 0px; margin-bottom: 12px; margin-left: 24px; padding: 0px; color: rgb(17, 17, 17); font-family: Helvetica, Arial, sans-serif;\"><li style=\"margin-bottom: 0px;\">Deterioro de la memoria, como por ejemplo, dificultad para recordar eventos</li><li style=\"margin-bottom: 0px;\">Dificultad para concentrarse, planificar o resolver problemas</li><li style=\"margin-bottom: 0px;\">Problemas para completar tareas diarias en el hogar o en el trabajo</li><li style=\"margin-bottom: 0px;\">Confusión con respecto a los lugares o el paso del tiempo</li><li style=\"margin-bottom: 0px;\">Dificultades visuales o de espacio, como por ejemplo, no comprender distancias al conducir, perderse o poner cosas en lugares equivocados</li><li style=\"margin-bottom: 0px;\">Problemas de lenguaje, como por ejemplo, problemas para encontrar palabras o vocabulario reducido al hablar o escribir</li><li style=\"margin-bottom: 0px;\">Mal juicio al tomar decisiones</li><li style=\"margin-bottom: 0px;\">Retraerse de eventos laborales o compromisos sociales</li><li style=\"margin-bottom: 0px;\">Cambios de humor, como depresión u otros cambios en el comportamiento y la personalidad</li></ul>',1),(12,'<ul><li style=\"margin: 0px 0px 4px; padding: 0px; list-style-type: disc;\">La edad. El riesgo de presión arterial alta aumenta con la edad.&nbsp;</li><li class=\"TrT0Xe\" style=\"margin: 0px 0px 4px; padding: 0px; list-style-type: disc;\">Raza.&nbsp;</li><li class=\"TrT0Xe\" style=\"margin: 0px 0px 4px; padding: 0px; list-style-type: disc;\">Antecedentes familiares.&nbsp;</li><li class=\"TrT0Xe\" style=\"margin: 0px 0px 4px; padding: 0px; list-style-type: disc;\">Tener sobrepeso u obesidad. </li><li class=\"TrT0Xe\" style=\"margin: 0px 0px 4px; padding: 0px; list-style-type: disc;\">No hacer actividad física.&nbsp;</li><li class=\"TrT0Xe\" style=\"margin: 0px 0px 4px; padding: 0px; list-style-type: disc;\">Consumo de tabaco.&nbsp;</li><li class=\"TrT0Xe\" style=\"margin: 0px 0px 4px; padding: 0px; list-style-type: disc;\">Demasiada sal (sodio) en la dieta.&nbsp;</li><li class=\"TrT0Xe\" style=\"margin: 0px 0px 4px; padding: 0px; list-style-type: disc;\">Muy poco potasio en la dieta.</li></ul>','<p><span style=\"color: rgb(17, 17, 17); font-family: Helvetica, Arial, sans-serif;\">La hipertensión sistólica aislada es un trastorno en que la presión diastólica es normal (menor que 80 mm&nbsp;Hg), pero la presión sistólica es alta (mayor que o igual a 130 mm&nbsp;Hg). Se trata de un tipo de presión arterial alta frecuente entre las personas mayores de 65 años.</span><br></p>','https://firebasestorage.googleapis.com/v0/b/treatmenttracer.appspot.com/o/images%2FHipertensi%C3%B3n?alt=media&token=6baf7294-b1b7-4634-9506-240586171bf5','Hipertensión','<p style=\"font-family: Helvetica, Arial, sans-serif; margin-right: 0px; margin-bottom: 24px; margin-left: 0px; padding: 0px; line-height: 1.5em; color: rgb(17, 17, 17);\">La mayoría de las personas con presión arterial alta no tienen signos ni síntomas, incluso si las lecturas de presión arterial alcanzan niveles peligrosamente elevados.</p><p style=\"font-family: Helvetica, Arial, sans-serif; margin-right: 0px; margin-bottom: 24px; margin-left: 0px; padding: 0px; line-height: 1.5em; color: rgb(17, 17, 17);\">Algunas personas con presión arterial alta pueden tener dolor de cabeza, dificultad para respirar o sangrado nasal, pero estos signos y síntomas no son específicos y, por lo general, no se presentan hasta que dicho trastorno alcanza una etapa grave o potencialmente fatal.</p>',1),(13,NULL,NULL,NULL,'Artritis',NULL,2);
/*!40000 ALTER TABLE `disease` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `measurement`
--

DROP TABLE IF EXISTS `measurement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `measurement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `unit` varchar(255) NOT NULL,
  `value` float NOT NULL,
  `routine` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkqjw84gt882hkp9f8vqcj6iu8` (`routine`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `measurement`
--

LOCK TABLES `measurement` WRITE;
/*!40000 ALTER TABLE `measurement` DISABLE KEYS */;
INSERT INTO `measurement` VALUES (26,'2020-12-04 00:00:00','g/dl',0.97,18),(27,'2020-12-06 00:00:00','g/dl',0.96,18),(28,'2020-12-03 00:00:00','g/dl',1.02,18),(29,'2020-12-02 00:00:00','g/dl',0.85,18),(30,'2020-12-07 00:00:00','Horas',1,17);
/*!40000 ALTER TABLE `measurement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicalappointment`
--

DROP TABLE IF EXISTS `medicalappointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicalappointment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `annotations` varchar(255) DEFAULT NULL,
  `appointment_date` date NOT NULL,
  `specialty` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `userid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKebgaxjlbg8snk1nrbkgryh3me` (`userid`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicalappointment`
--

LOCK TABLES `medicalappointment` WRITE;
/*!40000 ALTER TABLE `medicalappointment` DISABLE KEYS */;
INSERT INTO `medicalappointment` VALUES (12,'Hora: 8:45Am\r\nLlevar gráfica de medición de la glucosa.','2020-12-11','Nutricionista','Revisión peso',24),(13,'Acudir en ayunas a primera hora de la mañana','2020-12-16','Enfermeria','Analítica',24);
/*!40000 ALTER TABLE `medicalappointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicalfile`
--

DROP TABLE IF EXISTS `medicalfile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicalfile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `file` longtext NOT NULL,
  `title` varchar(255) NOT NULL,
  `uploaddate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `userid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe8b2lr8iju3yhckpcrch32ixd` (`userid`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicalfile`
--

LOCK TABLES `medicalfile` WRITE;
/*!40000 ALTER TABLE `medicalfile` DISABLE KEYS */;
INSERT INTO `medicalfile` VALUES (13,'Dieta del mes de noviembre','https://firebasestorage.googleapis.com/v0/b/treatmenttracer.appspot.com/o/files%2FDieta%20a%20seguir?alt=media&token=83d8b60c-2935-4b13-8cac-a2f1dd861be5','Dieta a seguir','2020-12-06 00:00:00',24),(14,'Ejercicios para adelgazar','https://firebasestorage.googleapis.com/v0/b/treatmenttracer.appspot.com/o/files%2FEjercicio?alt=media&token=edeef93e-0beb-44a6-9687-79822a23a7e3','Ejercicio','2020-12-07 00:00:00',24);
/*!40000 ALTER TABLE `medicalfile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_blob_triggers`
--

DROP TABLE IF EXISTS `qrtz_blob_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_blob_triggers`
--

LOCK TABLES `qrtz_blob_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_blob_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_blob_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_calendars`
--

DROP TABLE IF EXISTS `qrtz_calendars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_calendars`
--

LOCK TABLES `qrtz_calendars` WRITE;
/*!40000 ALTER TABLE `qrtz_calendars` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_calendars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_cron_triggers`
--

DROP TABLE IF EXISTS `qrtz_cron_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_cron_triggers`
--

LOCK TABLES `qrtz_cron_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_cron_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_cron_triggers` VALUES ('quartzScheduler','routineUpdateTrigger','DEFAULT','0 0 14 * * ?','Europe/Paris'),('quartzScheduler','treatmentUpdateTrigger','DEFAULT','0 0 14 * * ?','Europe/Paris');
/*!40000 ALTER TABLE `qrtz_cron_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_fired_triggers`
--

DROP TABLE IF EXISTS `qrtz_fired_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_fired_triggers`
--

LOCK TABLES `qrtz_fired_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_fired_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_fired_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_job_details`
--

DROP TABLE IF EXISTS `qrtz_job_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_job_details`
--

LOCK TABLES `qrtz_job_details` WRITE;
/*!40000 ALTER TABLE `qrtz_job_details` DISABLE KEYS */;
INSERT INTO `qrtz_job_details` VALUES ('quartzScheduler','routineUpdate','DEFAULT',NULL,'org.iesalixar.eponceg.quartz.RoutineUpdateJob','1','0','0','0',_binary '�\�\0sr\0org.quartz.JobDataMap���迩�\�\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�\�\��\�](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap\�.�(v\n\�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xp\0sr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0\0x\0'),('quartzScheduler','treatmentUpdate','DEFAULT',NULL,'org.iesalixar.eponceg.quartz.TreatmentUpdateJob','1','0','0','0',_binary '�\�\0sr\0org.quartz.JobDataMap���迩�\�\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�\�\��\�](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap\�.�(v\n\�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xp\0sr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0\0x\0');
/*!40000 ALTER TABLE `qrtz_job_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_locks`
--

DROP TABLE IF EXISTS `qrtz_locks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_locks`
--

LOCK TABLES `qrtz_locks` WRITE;
/*!40000 ALTER TABLE `qrtz_locks` DISABLE KEYS */;
INSERT INTO `qrtz_locks` VALUES ('quartzScheduler','STATE_ACCESS'),('quartzScheduler','TRIGGER_ACCESS');
/*!40000 ALTER TABLE `qrtz_locks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_paused_trigger_grps`
--

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_paused_trigger_grps`
--

LOCK TABLES `qrtz_paused_trigger_grps` WRITE;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_scheduler_state`
--

DROP TABLE IF EXISTS `qrtz_scheduler_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_scheduler_state`
--

LOCK TABLES `qrtz_scheduler_state` WRITE;
/*!40000 ALTER TABLE `qrtz_scheduler_state` DISABLE KEYS */;
INSERT INTO `qrtz_scheduler_state` VALUES ('quartzScheduler','DESKTOP-ME6HFEA1607340698497',1607345298087,7500);
/*!40000 ALTER TABLE `qrtz_scheduler_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simple_triggers`
--

DROP TABLE IF EXISTS `qrtz_simple_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simple_triggers`
--

LOCK TABLES `qrtz_simple_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simple_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simple_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simprop_triggers`
--

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simprop_triggers`
--

LOCK TABLES `qrtz_simprop_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_triggers`
--

DROP TABLE IF EXISTS `qrtz_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_triggers`
--

LOCK TABLES `qrtz_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_triggers` VALUES ('quartzScheduler','routineUpdateTrigger','DEFAULT','routineUpdate','DEFAULT',NULL,1607346000000,1607259600000,5,'WAITING','CRON',1605445458000,0,NULL,0,''),('quartzScheduler','treatmentUpdateTrigger','DEFAULT','treatmentUpdate','DEFAULT',NULL,1607346000000,1607259600000,5,'WAITING','CRON',1605444440000,0,NULL,0,'');
/*!40000 ALTER TABLE `qrtz_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'patient','patient'),(3,'admin','admin'),(2,'career','career');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roleusers`
--

DROP TABLE IF EXISTS `roleusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roleusers` (
  `usuario_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`usuario_id`,`role_id`),
  KEY `FK36ttc4gynvij19w1xe5qsb5wb` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roleusers`
--

LOCK TABLES `roleusers` WRITE;
/*!40000 ALTER TABLE `roleusers` DISABLE KEYS */;
INSERT INTO `roleusers` VALUES (14,3),(24,1),(25,2),(26,1),(27,1);
/*!40000 ALTER TABLE `roleusers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routine`
--

DROP TABLE IF EXISTS `routine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `routine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activationdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `duration` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `posology` int(11) NOT NULL,
  `disease` bigint(20) NOT NULL,
  `owneruser` bigint(20) NOT NULL,
  `routinestate` int(11) NOT NULL DEFAULT '1',
  `expirationdate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK6q4npwn73dm4a8ywfwygp20e1` (`disease`),
  KEY `FK91r7ii3dllwk8cbrghu2uq8t` (`owneruser`),
  KEY `FKkaysms6lc1p7pdwxtnlwnvcip` (`routinestate`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routine`
--

LOCK TABLES `routine` WRITE;
/*!40000 ALTER TABLE `routine` DISABLE KEYS */;
INSERT INTO `routine` VALUES (17,'2020-12-06 00:00:00',360,'Ejercicio Diario',24,2,24,1,'2020-12-21 00:00:00'),(18,'2020-12-06 00:00:00',720,'Glucosa en sangre',12,2,24,1,'2021-01-05 00:00:00');
/*!40000 ALTER TABLE `routine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `state` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` VALUES (1,'active'),(2,'non-active');
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatment`
--

DROP TABLE IF EXISTS `treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treatment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activationdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `duration` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `posology` int(11) NOT NULL,
  `disease` bigint(20) NOT NULL,
  `owneruser` bigint(20) NOT NULL,
  `treatmentstate` int(11) NOT NULL DEFAULT '1',
  `expirationdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK2dkk434h2pph4opfyme5ra3a7` (`disease`),
  KEY `FKnyh3o79acc083yo6u6y069gik` (`owneruser`),
  KEY `FKnjw9nlltilmo17pnso2t23bcw` (`treatmentstate`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment`
--

LOCK TABLES `treatment` WRITE;
/*!40000 ALTER TABLE `treatment` DISABLE KEYS */;
INSERT INTO `treatment` VALUES (13,'2020-12-06 00:00:00',360,'Insulina',24,2,24,1,'2020-12-21 00:00:00');
/*!40000 ALTER TABLE `treatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateofbirth` date NOT NULL,
  `dischargedate` datetime DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(45) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `career` bigint(20) DEFAULT NULL,
  `state` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  KEY `FKgt3nx24cu97wwbvr6twrc54lq` (`career`),
  KEY `FKbm2jiqp5m3tan4d9tap8qsk9l` (`state`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (14,'1998-02-02','2020-11-08 00:00:00','admin@admin.com','admin','$2a$04$y5TkEqezQHnZ8MSW5XCjquRKgTfsXmy8reQEJXfOfLyAAijrP6qim','admin',NULL,1),(24,'1997-09-17','2020-12-06 00:00:00','esther_virgo97@hotmail.com','Esther','$2a$04$gKz/KPXgYlrgzA0dyYnZ1.steM/GjDUWfrI2Qkg80pigKDuk68eke','Ponce García',NULL,1),(25,'1993-03-20','2020-12-06 00:00:00','alum.bluisrochag@iesalixar.org','Blas Luis','$2a$04$1uFmrku7b2pph9baoLK4TeYFMzWHbe6xB4lH19VCWvFAL11KZL.7y','Rocha González',NULL,1),(26,'1995-05-15','2020-12-06 00:00:00','a.martin@gmail.com','Ángel','$2a$04$qq/fdeNCtlca567Kr1bp3uEipVWzGlHveSzoAHxNjTezHqHDlaEkS','Martín López',25,1),(27,'1996-08-12','2020-12-06 00:00:00','perezpedro@gmail.com','Pedro','$2a$04$YdYAHtKvEP/HN27ArzOOU.T0uiqczuBKVLmaZjgvlH83xvIvmrU16','Perez',25,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userdisease`
--

DROP TABLE IF EXISTS `userdisease`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userdisease` (
  `disease` bigint(20) NOT NULL,
  `user` bigint(20) NOT NULL,
  PRIMARY KEY (`disease`,`user`),
  KEY `FKda7ebnous7wjvpfvbr35gibx6` (`user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userdisease`
--

LOCK TABLES `userdisease` WRITE;
/*!40000 ALTER TABLE `userdisease` DISABLE KEYS */;
INSERT INTO `userdisease` VALUES (1,15),(2,15),(2,24),(11,24),(13,24);
/*!40000 ALTER TABLE `userdisease` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-08 13:24:00
