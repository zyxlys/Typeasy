-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: localhost    Database: typeasy
-- ------------------------------------------------------
-- Server version	5.6.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `coid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cid` int(10) unsigned DEFAULT '0',
  `created` datetime DEFAULT NULL,
  `author` varchar(200) DEFAULT NULL,
  `authorId` int(10) unsigned DEFAULT '0',
  `ownerId` int(10) unsigned DEFAULT '0',
  `mail` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `agent` varchar(200) DEFAULT NULL,
  `text` text,
  `type` varchar(16) DEFAULT 'comment',
  `status` varchar(16) DEFAULT 'approved',
  `parent` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`coid`),
  KEY `cid` (`cid`),
  KEY `created` (`created`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (11,87,'2013-06-06 14:05:45','浅末',1,1,'imomome@imomo.me','http://imomo.me',NULL,NULL,'大爱杰伦~','comment','approved',0),(12,1,'2013-06-06 14:05:56','浅末',1,1,'imomome@imomo.me','http://imomo.me',NULL,NULL,'这是一篇测试评论','comment','approved',0),(13,89,'2013-06-06 14:06:10','浅末',1,1,'imomome@imomo.me','http://imomo.me',NULL,NULL,'一直都很幸福。','comment','approved',0),(14,88,'2013-06-06 14:07:16','Mr. M',7,1,'shallowmo@outlook.com','http://weibo.com/acris',NULL,NULL,'平淡中带着甜蜜 哈哈','comment','approved',0),(15,95,'2013-06-06 16:00:36','Mr. M',7,7,'shallowmo@outlook.com','http://weibo.com/acris',NULL,NULL,'自在，悠然无对错，何乐都是醉。','comment','approved',0);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contents`
--

DROP TABLE IF EXISTS `contents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contents` (
  `cid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `slug` varchar(200) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `text` text,
  `order` int(10) unsigned DEFAULT '0',
  `authorId` int(10) unsigned DEFAULT '0',
  `template` varchar(32) DEFAULT NULL,
  `type` varchar(16) DEFAULT 'post',
  `status` varchar(16) DEFAULT 'publish',
  `password` varchar(32) DEFAULT NULL,
  `commentsNum` int(10) unsigned DEFAULT '0',
  `allowComment` char(1) DEFAULT '0',
  `allowPing` char(1) DEFAULT '0',
  `allowFeed` char(1) DEFAULT '0',
  PRIMARY KEY (`cid`),
  KEY `created` (`created`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contents`
--

LOCK TABLES `contents` WRITE;
/*!40000 ALTER TABLE `contents` DISABLE KEYS */;
INSERT INTO `contents` VALUES (1,'欢迎使用Typeasy博客系统','欢迎使用Typeasy博客系统','2013-06-06 13:59:47',NULL,'<p>\r\n	Typeasy是一个使用jsp+MVC框架编写的一个轻量级博客程序。\r\n</p>\r\n<p>\r\n	Typeasy名字源于英语单词type和easy的组合，寓意在于让你打字变得很简单。这里的打字就是代表这写博客。你能用它来部署属于你自己的Blog。 <br />\r\nTypeasy拥有免费、开源、轻量、安全、人性化和操作简单等一系列的优点。它能让你记录下生活中的每一刻。当前发布的版本为1.0版本，由于时间和计数的问题，程序难免存在纰漏。如果您发现有任何的错误，或者有任何的意见和建议，欢迎您联系我们：\r\n</p>\r\n<ul>\r\n	<li>\r\n		E-Mail: shallowmo@outlook.com\r\n	</li>\r\n	<li>\r\n		Q&nbsp; Q: 2235515581\r\n	</li>\r\n	<li>\r\n		Github: http://github.com/Acris/Typeasy\r\n	</li>\r\n</ul>',0,1,NULL,'post','publish',NULL,1,'0','0','0'),(76,'关于','关于','2013-06-06 13:24:06','2013-06-06 17:03:10','<p>\r\n	感谢您使用Typeasy博客系统。\r\n</p>\r\n<p>\r\n	Typeasy是一个使用jsp+MVC框架编写的一个轻量级博客程序。\r\n</p>\r\n<p>\r\n	Typeasy名字源于英语单词type和easy的组合，寓意在于让你打字变得很简单。这里的打字就是代表着写博客。你能用它来部署属于你自己的Blog。 <br />\r\nTypeasy拥有免费、开源、轻量、安全、人性化和操作简单等一系列的优点。它能让你记录下生活中的每一刻。\r\n</p>\r\n<p>\r\n	<br />\r\n</p>',0,1,NULL,'page','publish',NULL,0,'0','0','0'),(77,'联系','联系','2013-06-06 13:24:31','2013-06-06 13:26:11','<p>\r\n	当前发布的版本为1.0版本，由于时间和计数的问题，程序难免存在纰漏。如果您发现有任何的错误，或者有任何的意见和建议，欢迎您联系我们：\r\n</p>\r\n<p>\r\n	<br />\r\n</p>\r\n<ul>\r\n	<li>\r\n		<span style=\"color:#E53333;\">E-Mail: shallowmo@outlook.com</span>\r\n	</li>\r\n	<li>\r\n		<span style=\"color:#E53333;\">Q&nbsp; Q: 2235515581</span>\r\n	</li>\r\n	<li>\r\n		<span style=\"color:#E53333;\">Github: </span><a href=\"http://github.com/Acris/Typeasy\"><span style=\"color:#E53333;\">http://github.com/Acris/Typeasy</span></a> \r\n	</li>\r\n</ul>\r\n<p>\r\n	&nbsp;\r\n</p>\r\n<img alt=\"\" src=\"http://127.0.0.1:8080/Typeasy/kindeditor/plugins/emoticons/images/0.gif\" border=\"0\" /> \r\n<p>\r\n	<br />\r\n</p>',0,1,NULL,'page','publish',NULL,0,'0','0','0'),(87,'简单爱','简单爱','2013-06-06 13:55:56',NULL,'<p>\r\n	说不上为什么 我变得很主动<br />\r\n若爱上一个人 什么都会值得去做<br />\r\n我想大声宣布 对你依依不舍<br />\r\n连隔壁邻居都猜到我现在的感受\r\n</p>\r\n<p>\r\n	河边的风 在吹着头发飘动<br />\r\n牵着你的手 一阵莫名感动<br />\r\n我想带你 回我的外婆家<br />\r\n一起看着日落 一直到我们都睡着\r\n</p>\r\n<p>\r\n	我想就这样牵着你的手不放开<br />\r\n爱能不能够永远单纯没有悲哀<br />\r\n我 想带你骑单车<br />\r\n我 想和你看棒球<br />\r\n想这样没担忧 唱着歌 一直走\r\n</p>\r\n<p>\r\n	我想就这样牵着你的手不放开<br />\r\n爱可不可以简简单单没有伤害<br />\r\n你 靠着我的肩膀<br />\r\n你 在我胸口睡着<br />\r\n像这样的生活 我爱你 你爱我\r\n</p>\r\n<p>\r\n	想 简！简！单！单！ 爱…<br />\r\n想 简！简！单！单！ 爱…\r\n</p>',0,1,NULL,'post','publish',NULL,1,'0','0','0'),(88,'平淡的生活','平淡的生活','2013-06-06 13:56:41','2013-06-06 13:56:49','经常有写些东西的冲动，可每次提笔时却不知道写些什么。是啊，自己的生活圈只有如此的狭小，视野也不会宽广到那里去了。每天过着上下班的平淡生活，所以有时一个人的时候，真的很期待时钟能走得再快些，说不定下一个时刻会有一个小小的意外呢，这样至少不会觉得太单调、乏味了。有时候又很期待着新一年的到来，想像着在新的一年里会发生什么样的事情。到后来才发现我把一切美好都想像在自己身上了，甜蜜的爱情、真挚的友情、健康的身体、高薪的工作和幸福和谐的家人，一切一切都是如此美好。是啊，生活本是美好的，只要我们怀着一颗感恩、易如满足的心去对待身边的人和事，那么即使是一个微笑、一声招呼都是幸福的。那既是这样，即使我那美好的想像会有一点点的不如意，那有怎样呢？',0,1,NULL,'post','publish',NULL,1,'0','0','0'),(89,'要很幸福','要很幸福','2013-06-06 13:58:18','2013-06-06 13:58:31','也不知道从什么时候起喜欢上了上网看别人博客，喜欢上了那些感伤的文字，看着那些字眼，体会着笔者的忧伤，体会着笔者的心痛。看到他们的爱情故事由美丽的开始到最后的天南地北、曲终人散，很为他们感到惋惜。难道一段美丽的爱情注定要有着凄美的结局吗？又为什么相爱的两个人却要彼此的伤害呢？有时候真的很害怕，害怕自己会成为剧中的女主角，害怕那种撕心裂肺，独自泪流的痛。其实我并不是那种多愁善感的女孩，但当面对感情时，我想每个人都会变得很脆弱吧。想想身边的朋友，有的已经有了自己的另一半，有的还在寻觅中，有的仍是孤单一人，不管找到的，没找到的，都希望你们幸福，毕竟谁也没有剥夺我们幸福的权利，不是吗？',0,1,NULL,'post','publish',NULL,1,'0','0','0'),(95,'旧城以南 忘川以北','旧城以南 忘川以北','2013-06-06 15:50:54',NULL,'<p>\r\n	旧城以南，忘川以北。\r\n</p>\r\n<p>\r\n	或天寒地冻，或日光倾城。\r\n</p>\r\n<p>\r\n	盼一场轰轰烈烈，待一场繁华雨烟。\r\n</p>\r\n<p>\r\n	把手一段相濡以沫，轻送一场落幕离别。\r\n</p>\r\n<p>\r\n	平平淡淡，简简单单，只为求一场虚幻。\r\n</p>\r\n<p>\r\n	爱情本无罪，只是世人乱许妄言，夸乎其谈，如痴如梦。\r\n</p>\r\n<p>\r\n	归一片清浅，留一抹寂然。\r\n</p>\r\n<p>\r\n	若我不痴，又何必感叹。若我不爱，又何须默然。\r\n</p>\r\n<p>\r\n	只怪流连忘返，迷失情雾其间。\r\n</p>\r\n<p>\r\n	旧城以南，未到过的我曾以为是一片温暖。\r\n</p>\r\n<p>\r\n	光彩入怀，不顾先前以后，与城共忘偏偏。\r\n</p>\r\n<p>\r\n	忘川以北，曾听说过的我以为定是一城寒雪。\r\n</p>\r\n<p>\r\n	阻隔天边，只怕一不小心，落入冰雪覆眉。\r\n</p>\r\n<p>\r\n	如若有三生红线，定与你世世不别。\r\n</p>\r\n<p>\r\n	南之南，北之北，情何以堪。\r\n</p>\r\n<p>\r\n	可是。\r\n</p>\r\n<p>\r\n	若你之北，我却居南，相愿相爱，是否会彼此渲染。\r\n</p>\r\n<p>\r\n	望一波秋水，解一桩心事。\r\n</p>\r\n<p>\r\n	是非对错，敢问，不敢听。\r\n</p>\r\n<p>\r\n	来年种下一株花，盼它开出片片繁华。\r\n</p>\r\n<p>\r\n	爱情是生命里的一抹春光，不论南北，无关冰雪或是烈日。\r\n</p>\r\n<p>\r\n	幸福那种滋味，就是一个奢华的愿望。\r\n</p>\r\n<p>\r\n	爱情那种感觉，就是一个天大的惊喜。\r\n</p>\r\n<p>\r\n	我们再也不会遇见爱情。\r\n</p>\r\n<p>\r\n	因为太过执着，年轻的时候便已殉情，死掉。\r\n</p>\r\n<p>\r\n	再不会有它的模样。\r\n</p>\r\n<p>\r\n	仿佛一阵风过，可以吹散那些雨烟。\r\n</p>\r\n<p>\r\n	似若一枝独木，可以不再害怕孤单。\r\n</p>\r\n<p>\r\n	那，叫看破。\r\n</p>\r\n<p>\r\n	刹那可以永远，永远不可停留。\r\n</p>\r\n<p>\r\n	再不去问，只因都明了，再不去说，只因都懂得。\r\n</p>\r\n<p>\r\n	相遇与错过，都只是天意，欢喜只是存在记忆里的感情。\r\n</p>\r\n<p>\r\n	我也曾那般天真。\r\n</p>\r\n<p>\r\n	春色里的飘絮，秋日中的红枫，正是色彩对时光的点缀。\r\n</p>\r\n<p>\r\n	岁月时长，没有哪种痛苦是为你单独而备。\r\n</p>\r\n<p>\r\n	旧城以南，忘川以北。\r\n</p>\r\n<p>\r\n	寂然，撩乱。\r\n</p>\r\n<p>\r\n	安静，独享。\r\n</p>\r\n<p>\r\n	昔人，忘世。\r\n</p>\r\n<p>\r\n	虚幻，随缘。\r\n</p>\r\n<p>\r\n	岁月无理，或是我无知。\r\n</p>\r\n<p>\r\n	年华美丽，或是我错失。\r\n</p>\r\n<p>\r\n	若不理世事沉浮，不管阴晴圆缺，不论过错是非。\r\n</p>\r\n<p>\r\n	若笑对万千虚假，淡然无所不知，轻放一席相思。\r\n</p>\r\n<p>\r\n	这，是放下。\r\n</p>\r\n<p>\r\n	年轻的自己，是可以承受风暴的载体。\r\n</p>\r\n<p>\r\n	别怕若即若离，才会事不关己。\r\n</p>\r\n<p>\r\n	总有一天会是天晴，不再有雨。\r\n</p>\r\n<p>\r\n	我不愿知是非错对。\r\n</p>\r\n<p>\r\n	我只盼悠然自乐，无恙无灾。\r\n</p>\r\n<p>\r\n	再看那些轰轰烈烈，那场繁华雨烟。\r\n</p>\r\n<p>\r\n	那些揪心不舍，那些别离难忘，那些幸福快乐，那些清秀容颜。\r\n</p>\r\n<p>\r\n	仿若隔世般的温暖，遗忘千载的牵连。\r\n</p>\r\n<p>\r\n	明媚的阳光，刺心的疼痛。\r\n</p>\r\n<p>\r\n	一抹云烟，一丝清水。\r\n</p>\r\n<p>\r\n	无妄，自在。\r\n</p>\r\n<p>\r\n	看破，放下，自在。\r\n</p>\r\n<p>\r\n	我怕做到。\r\n</p>\r\n<p>\r\n	太多缘分，总要经过兵荒马乱。\r\n</p>\r\n<p>\r\n	握的越紧，消的越快。\r\n</p>\r\n<p>\r\n	旧城以南有它的风光，炽热而别样。\r\n</p>\r\n<p>\r\n	忘川以北有它的执着，冰冻而倔强。\r\n</p>\r\n<p>\r\n	每个人，也都有有他的模样。\r\n</p>\r\n<p>\r\n	烟花雨巷，尘峦如土。\r\n</p>\r\n<p>\r\n	有些眷恋，有些不舍，何必牵强。\r\n</p>\r\n<p>\r\n	人生数十载，无非过往。\r\n</p>\r\n<p>\r\n	欺一面哭泣的脸，负一件无力的事，情非得以。\r\n</p>\r\n<p>\r\n	安年无念，自寻自在。\r\n</p>\r\n<p>\r\n	平淡是福，自在是幸福。\r\n</p>\r\n<p>\r\n	南北之地，除却风雪暖阳，也有人心情长。\r\n</p>\r\n<p>\r\n	看破，往事皆红尘，飘渺何需愁。\r\n</p>\r\n<p>\r\n	放下，过往净美丽，怨我心沉沦。\r\n</p>\r\n<p>\r\n	自在，悠然无对错，何乐都是醉。\r\n</p>\r\n<p>\r\n	纯净，绚丽。\r\n</p>\r\n<p>\r\n	素色，真切。\r\n</p>\r\n<p>\r\n	旧城以南，忘川以北。\r\n</p>\r\n<p>\r\n	何去，何从。\r\n</p>',0,7,NULL,'post','publish',NULL,1,'0','0','0'),(96,'临界﹑爵迹','临界﹑爵迹','2013-06-06 15:51:50','2013-06-06 15:52:55','<p>\r\n	众神的黄昏陨落后，浑浊不堪的时代覆灭了神话，玛雅后裔遭遇了史前毁灭，化为一切尘埃，死神逐渐逼近，世界沦为杀戮的战场，鲜血飞溅，落到泛黄符咒印的墙上，触动了神忆时代众神留下的临界﹑亡灵飘荡的界面。\r\n</p>\r\n<p>\r\n	临界﹑弑神者畏惧的坟墓，生命低贱得毫无价值，远方走来一位手持宛若镰刀的少年，挥舞着灵魂的收割，魂飞魄散，陨落的众神悲哀地呻吟，少年面无表情地执行死神的救赎，夕阳被血液染成血红色，不安地懆动着，临界中战败后插在地上的旗帜，周围是尸横遍野的墓碑，少年眼眸里急速扩大的血瞳，给人无尽的空洞，嘴上扬起一丝玩昧的笑容，谁也不知﹑是末日，或是天堂。瞳孔里幻化的世界，漂浮着流失的残魂，咒怨出亡界的恶灵，噬魂成魄，借体成人，喻为无止境的吸血鬼，寄生在黑暗中潮湿隐隠的角落里，潜伏瞬间﹑獠牙咬向鲜美多汁的猎物，聆听死前痛苦的哀嚎，美丽的声音，谱写了生命悲哀的末曲。\r\n</p>\r\n<p>\r\n	少年代言了死神的职责，维持临界永恒不变的规则，刀刃上的鲜血，是背叛者的惩罚，罪恶的救赎只是一场善意的谎言，安抚心灵反抗的战场，临界一点点杀戮气息，远古爵迹苏醒，神话时代早已忘却，少年迈出终结一切的脚印，主角注定同归于尽，大地被打上血红色的烙印，何为临界？无尽杀戮，无尽虚伪贪婪，的人心，上帝创造人类，人类背叛了上帝，死亡河流逆流而上，永恒存在的月光，开始无尽的黯淡，历史记住了成功的辉煌，抹去了失败的一切。\r\n</p>\r\n<p>\r\n	少年毅然踏进了未知的死亡-爵迹。雕塑的图画华丽逼真，呈现了一个辉煌浩大的王朝，谁，主宰着临界的天地，陨落的众神埋葬了一切，探索停止了罪恶的追赎，爵迹关上了，少年注定停止毁灭，临界开始了轮回的时代，众神忘却了，爵迹里存在什么，谁也不知，天堂里嘻哈的天使，正在安逸地生活，残留脑海里一丝罪恶，其实一切只是场开始。\r\n</p>\r\n<p>\r\n	临界，一个让无尽贪婪追溯的爵迹，没有停止的罪恶，开始了新鲜的杀戮。\r\n</p>',0,7,NULL,'post','publish',NULL,0,'0','0','0');
/*!40000 ALTER TABLE `contents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metas`
--

DROP TABLE IF EXISTS `metas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `metas` (
  `mid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `slug` varchar(200) DEFAULT NULL,
  `type` varchar(32) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `count` int(10) unsigned DEFAULT '0',
  `order` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`mid`),
  KEY `slug` (`slug`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metas`
--

LOCK TABLES `metas` WRITE;
/*!40000 ALTER TABLE `metas` DISABLE KEYS */;
INSERT INTO `metas` VALUES (51,'随笔','eassay','category','记录生活中的点点滴滴。',3,0),(52,'默认','default','category','这是系统自动生成的分类目录。',2,0),(53,'音乐','music','category','聆听天籁之音。',1,0),(54,'周杰伦',NULL,'tag',NULL,1,0),(55,'生活',NULL,'tag',NULL,1,0),(56,'唯美',NULL,'tag',NULL,1,0),(57,'爱情',NULL,'tag',NULL,2,0),(58,'Typeasy',NULL,'tag',NULL,1,0),(59,'心情',NULL,'tag',NULL,1,0),(60,'故事','story','category','探索不为人知的秘密。',0,0),(61,'神话',NULL,'tag',NULL,1,0);
/*!40000 ALTER TABLE `metas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `options` (
  `name` varchar(32) NOT NULL,
  `user` int(10) unsigned NOT NULL DEFAULT '0',
  `value` text,
  PRIMARY KEY (`name`,`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES ('count',0,'500'),('date',0,'yyyy年MM月dd日'),('description',0,'记录下生活中的点点滴滴'),('excerpt',0,'excerpt'),('number',0,'5'),('time',0,'HH时mm分'),('title',0,'Typeasy');
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relationships`
--

DROP TABLE IF EXISTS `relationships`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relationships` (
  `cid` int(10) NOT NULL,
  `mid` int(10) NOT NULL,
  PRIMARY KEY (`cid`,`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relationships`
--

LOCK TABLES `relationships` WRITE;
/*!40000 ALTER TABLE `relationships` DISABLE KEYS */;
INSERT INTO `relationships` VALUES (1,52),(1,58),(87,53),(87,54),(88,51),(88,55),(89,51),(89,56),(89,57),(95,51),(95,57),(96,59),(96,60),(96,61);
/*!40000 ALTER TABLE `relationships` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `mail` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `screenName` varchar(32) DEFAULT NULL,
  `avatar` varchar(200) DEFAULT 'images/default.jpg',
  `created` datetime DEFAULT NULL,
  `activated` datetime DEFAULT NULL,
  `logged` datetime DEFAULT NULL,
  `group` varchar(16) DEFAULT 'visitor',
  `authCode` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `mail` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','E10ADC3949BA59ABBE56E057F20F883E','imomome@imomo.me','http://imomo.me','浅末','images/default.jpg','2013-05-28 00:08:04',NULL,NULL,'admin',NULL),(7,'shallowmo','4297F44B13955235245B2497399D7A93','shallowmo@outlook.com','http://weibo.com/acris','Mr. M','images/default.jpg','2013-06-06 00:16:46',NULL,NULL,'visitor',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-06-06 20:41:55
