/*
 Navicat Premium Data Transfer

 Source Server         : TencentCloud
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : 121.4.179.240:3306
 Source Schema         : emergencyguide

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 22/05/2021 15:47:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer`  (
  `id` int(0) NOT NULL,
  `openId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `realName` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickName` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobilePhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `registerTime` datetime(0) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `profession` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES (1, '1', '男', NULL, '陈杰', NULL, '18302394623', '2021-05-12 00:07:25', '2425496695@qq.com', '学生');

-- ----------------------------
-- Table structure for t_customerQuestion
-- ----------------------------
DROP TABLE IF EXISTS `t_customerQuestion`;
CREATE TABLE `t_customerQuestion`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `customerId` int(0) NULL DEFAULT NULL,
  `questionId` int(0) NULL DEFAULT NULL,
  `AnswerRight` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_customerQuestion
-- ----------------------------

-- ----------------------------
-- Table structure for t_disaster
-- ----------------------------
DROP TABLE IF EXISTS `t_disaster`;
CREATE TABLE `t_disaster`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `disastertype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `solution` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_disaster
-- ----------------------------
INSERT INTO `t_disaster` VALUES (1, '火灾', '发现火灾迅速拨打火警电话119。报警时要讲清详细地址、起火部位、着火物质、火势大小、报警人姓名及电话号码并派人到路口迎接消防车。\r\n\r\n　　家中一旦失火，不要惊慌失措，如果火势不大，迅速利用家中备有的简易灭火器材，采取有效措施控制和扑灭火灾。\r\n\r\n　　油锅着火，不能泼水灭火，应关闭炉灶燃气阀门，直接盖上锅盖或用湿抹布覆盖，令火熄灭。还可向锅内放入切好的蔬菜冷却灭火。\r\n\r\n　　家用电器着火，要先切断电源，再用干粉或气体灭火器灭火，不可直接泼水灭火，以防触电或电器爆炸伤人。\r\n\r\n　　救火时不要贸然开门窗，以免空气对流，加速火势蔓延。\r\n\r\n　　家庭成员平时要了解掌握火灾逃生的基本方法，熟悉几条逃生路线。\r\n\r\n　　室外着火，门已发烫时，千万不要开门，以防火势蹿入屋内。要用浸湿的被褥、衣物等堵塞门窗缝，并泼水降温。\r\n\r\n　　遇火不可坐电梯，要向安全出口方向逃生。\r\n\r\n　　火灾袭来时要迅速逃生，不要贪恋财物。\r\n\r\n　　受到火灾威胁时，要当机立断披上浸湿的衣物、被褥等向安全出口方向冲去。\r\n\r\n　　穿过浓烟逃生时，要尽量使身体贴近地面，并用湿毛巾捂住口鼻。\r\n\r\n　　若所有逃生路线被大火封锁，要立即退回室内，用打手电筒、挥衣物、呼叫等方式向窗外发送求救信号，等待救援。\r\n\r\n　　身上着火，千万不要奔跑，可就地打滚用厚重的衣物压灭火苗。\r\n\r\n　　千万不要盲目跳楼，可利用疏散楼梯、阳台、落水管等逃生自救。也可以用绳子或把床单、被套撕成条状连成绳索，紧拴在窗框、暖气管、铁栏杆等固定物上，用湿毛巾、布条等保护手心，顺绳滑下，下到未着火的楼层脱离险境。');
INSERT INTO `t_disaster` VALUES (4, '火灾', '发现火灾迅速拨打火警电话119。报警时要讲清详细地址、起火部位、着火物质、火势大小、报警人姓名及电话号码并派人到路口迎接消防车。\r\n\r\n　　家中一旦失火，不要惊慌失措，如果火势不大，迅速利用家中备有的简易灭火器材，采取有效措施控制和扑灭火灾。\r\n\r\n　　油锅着火，不能泼水灭火，应关闭炉灶燃气阀门，直接盖上锅盖或用湿抹布覆盖，令火熄灭。还可向锅内放入切好的蔬菜冷却灭火。\r\n\r\n　　家用电器着火，要先切断电源，再用干粉或气体灭火器灭火，不可直接泼水灭火，以防触电或电器爆炸伤人。\r\n\r\n　　救火时不要贸然开门窗，以免空气对流，加速火势蔓延。\r\n\r\n　　家庭成员平时要了解掌握火灾逃生的基本方法，熟悉几条逃生路线。\r\n\r\n　　室外着火，门已发烫时，千万不要开门，以防火势蹿入屋内。要用浸湿的被褥、衣物等堵塞门窗缝，并泼水降温。\r\n\r\n　　遇火不可坐电梯，要向安全出口方向逃生。\r\n\r\n　　火灾袭来时要迅速逃生，不要贪恋财物。\r\n\r\n　　受到火灾威胁时，要当机立断披上浸湿的衣物、被褥等向安全出口方向冲去。\r\n\r\n　　穿过浓烟逃生时，要尽量使身体贴近地面，并用湿毛巾捂住口鼻。\r\n\r\n　　若所有逃生路线被大火封锁，要立即退回室内，用打手电筒、挥衣物、呼叫等方式向窗外发送求救信号，等待救援。\r\n\r\n　　身上着火，千万不要奔跑，可就地打滚用厚重的衣物压灭火苗。\r\n\r\n　　千万不要盲目跳楼，可利用疏散楼梯、阳台、落水管等逃生自救。也可以用绳子或把床单、被套撕成条状连成绳索，紧拴在窗框、暖气管、铁栏杆等固定物上，用湿毛巾、布条等保护手心，顺绳滑下，下到未着火的楼层脱离险境。');
INSERT INTO `t_disaster` VALUES (5, '火灾', '发现火灾迅速拨打火警电话119。报警时要讲清详细地址、起火部位、着火物质、火势大小、报警人姓名及电话号码并派人到路口迎接消防车。\r\n\r\n　　家中一旦失火，不要惊慌失措，如果火势不大，迅速利用家中备有的简易灭火器材，采取有效措施控制和扑灭火灾。\r\n\r\n　　油锅着火，不能泼水灭火，应关闭炉灶燃气阀门，直接盖上锅盖或用湿抹布覆盖，令火熄灭。还可向锅内放入切好的蔬菜冷却灭火。\r\n\r\n　　家用电器着火，要先切断电源，再用干粉或气体灭火器灭火，不可直接泼水灭火，以防触电或电器爆炸伤人。\r\n\r\n　　救火时不要贸然开门窗，以免空气对流，加速火势蔓延。\r\n\r\n　　家庭成员平时要了解掌握火灾逃生的基本方法，熟悉几条逃生路线。\r\n\r\n　　室外着火，门已发烫时，千万不要开门，以防火势蹿入屋内。要用浸湿的被褥、衣物等堵塞门窗缝，并泼水降温。\r\n\r\n　　遇火不可坐电梯，要向安全出口方向逃生。\r\n\r\n　　火灾袭来时要迅速逃生，不要贪恋财物。\r\n\r\n　　受到火灾威胁时，要当机立断披上浸湿的衣物、被褥等向安全出口方向冲去。\r\n\r\n　　穿过浓烟逃生时，要尽量使身体贴近地面，并用湿毛巾捂住口鼻。\r\n\r\n　　若所有逃生路线被大火封锁，要立即退回室内，用打手电筒、挥衣物、呼叫等方式向窗外发送求救信号，等待救援。\r\n\r\n　　身上着火，千万不要奔跑，可就地打滚用厚重的衣物压灭火苗。\r\n\r\n　　千万不要盲目跳楼，可利用疏散楼梯、阳台、落水管等逃生自救。也可以用绳子或把床单、被套撕成条状连成绳索，紧拴在窗框、暖气管、铁栏杆等固定物上，用湿毛巾、布条等保护手心，顺绳滑下，下到未着火的楼层脱离险境。');
INSERT INTO `t_disaster` VALUES (6, '泥石流', '石流来了往哪跑？\r\n\r\n \r\n\r\n发现有泥石流迹象时，我们应该沉着冷静，不要在谷地停留，应迅速向两侧山坡或高地逃离，并尽快在周围寻找安全地带。一旦发现泥石流，要立即往与泥石流成垂直方向一边的山坡上跑，跑得越高、越快越好，一定不能向泥石流的流动方向逃生。往泥石流的两边跑也是有学问的，如果两边都是山坡，那么要注意可能会发生坡面性泥石流，应该跑向缓一些的山坡。另外，泥石流行至沟谷有些拐弯的地方，不能像水一样拐弯，就会爬高，因此逃生时不能往拐弯的地方走。最后，一定要记得遇到泥石流的时候不能跑直线。\r\n\r\n \r\n\r\n如果来不及跑怎么办？\r\n\r\n \r\n\r\n如果泥石流来临，已来不及逃跑，或者无法继续逃离时，应该迅速抱住身边的树木等固定物体，但不要上树躲避，因为泥石流不同于一般洪水，它在流动中可能剪断树木并将其卷入泥石流。躲避时，不要躲在有滚石和大量堆积物的陡峭山坡下面。如果在房间内，一定要设法从房屋里跑出来，到开阔地带，防止被埋压。遇到山体滑坡时，如果躲避不及，要注意保护好头部，用身边的衣物裹住头部。\r\n\r\n \r\n\r\n跑的时候要注意什么？\r\n\r\n \r\n\r\n逃跑时，一定要抛弃一切影响奔跑速度的物品，如挎包、旅行背包等，也不要因收拾家中财物而错过最佳逃生时机。如果有需要照顾的老年人、儿童、病人等，更要提前逃跑。跑动时，应注意查看前方道路是否有塌方、沟壑等，并随时小心可能出现的各种危险，如掉落的石头、树枝等。要躲到离泥石流发生地较远处的高地上，一定不要站在泥石流岸边观看。另外，一定不要试图横穿泥石流，这是非常危险的。');

-- ----------------------------
-- Table structure for t_disasterType
-- ----------------------------
DROP TABLE IF EXISTS `t_disasterType`;
CREATE TABLE `t_disasterType`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `disasterNumber` int(0) NULL DEFAULT NULL,
  `disasterType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `solution` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `disasterNumber`(`disasterNumber`) USING BTREE,
  CONSTRAINT `t_disasterType_ibfk_1` FOREIGN KEY (`disasterNumber`) REFERENCES `t_disaster` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_disasterType
-- ----------------------------
INSERT INTO `t_disasterType` VALUES (1, 1, '油锅', '不能泼水灭火，应关闭炉灶燃气阀门，直接盖上锅盖或用湿抹布覆盖，令火熄灭。还可向锅内放入切好的蔬菜冷却灭火。');
INSERT INTO `t_disasterType` VALUES (2, 1, '家用电器着火', '要先切断电源，再用干粉或气体灭火器灭火，不可直接泼水灭火，以防触电或电器爆炸伤人。');
INSERT INTO `t_disasterType` VALUES (5, 1, '水灾', '水灾');
INSERT INTO `t_disasterType` VALUES (7, 6, '小型泥石流', '芜湖');
INSERT INTO `t_disasterType` VALUES (8, 1, '水灾', '水灾');

-- ----------------------------
-- Table structure for t_questionAnswer
-- ----------------------------
DROP TABLE IF EXISTS `t_questionAnswer`;
CREATE TABLE `t_questionAnswer`  (
  `id` int(0) NOT NULL,
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `optionA` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `optionB` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `optionC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_questionAnswer
-- ----------------------------
INSERT INTO `t_questionAnswer` VALUES (1, '1+1等于多少', '1', '2', '3', '1');
INSERT INTO `t_questionAnswer` VALUES (2, '陈杰帅不帅', '小帅', '丑', '垃圾', '小帅');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `Id` int(0) NOT NULL AUTO_INCREMENT COMMENT '唯一ID',
  `Role` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `Authority` int(0) NULL DEFAULT NULL COMMENT '权限级别',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `Authority`(`Authority`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '系统管理员', 0);
INSERT INTO `t_role` VALUES (2, '普通管理员', 1);
INSERT INTO `t_role` VALUES (3, '崽种', 9);

-- ----------------------------
-- Table structure for t_systemconfig
-- ----------------------------
DROP TABLE IF EXISTS `t_systemconfig`;
CREATE TABLE `t_systemconfig`  (
  `Id` int(0) NOT NULL,
  `PayTime` int(0) NULL DEFAULT NULL COMMENT '支付完成时间 单位 分',
  `AutoFinishTime` int(0) NULL DEFAULT NULL COMMENT '自动确认结束时长 单位 小时',
  `ServicePhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务电话',
  `OrderCancelTime` int(0) NULL DEFAULT NULL COMMENT '订单取消时长 单位 分',
  `RefundPhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平台退款联系电话',
  `MsgCount` int(0) NULL DEFAULT NULL COMMENT '每日短信条数限制',
  `MsgTime` int(0) NULL DEFAULT NULL COMMENT '短信验证码有效期 单位 秒',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_systemconfig
-- ----------------------------
INSERT INTO `t_systemconfig` VALUES (1, 125, 45, '1336232123213', 5522, '12312323434', 20, 60);

-- ----------------------------
-- Table structure for t_systemuser
-- ----------------------------
DROP TABLE IF EXISTS `t_systemuser`;
CREATE TABLE `t_systemuser`  (
  `Id` int(0) NOT NULL AUTO_INCREMENT,
  `Username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Realname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `Authority` int(0) NULL DEFAULT NULL COMMENT '权限',
  `Status` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '启用状态',
  PRIMARY KEY (`Id`) USING BTREE,
  UNIQUE INDEX `UNIQUE_USERNAME`(`Username`) USING BTREE COMMENT '用户名需要唯一',
  INDEX `FK1`(`Authority`) USING BTREE,
  CONSTRAINT `FK1` FOREIGN KEY (`Authority`) REFERENCES `t_role` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_systemuser
-- ----------------------------
INSERT INTO `t_systemuser` VALUES (1, 'DarckLH', '123', '林海', '', 1, '启用');
INSERT INTO `t_systemuser` VALUES (2, 'Nanahira', '123', 'sdkf', 'asdsa', 1, '禁用');
INSERT INTO `t_systemuser` VALUES (3, 'Koikatsu', '123', 'lianhuo', '中文测试', 1, '启用');
INSERT INTO `t_systemuser` VALUES (4, 'chenjie', '123', '陈杰', '陈杰', 1, '启用');
INSERT INTO `t_systemuser` VALUES (5, 'test', '123', NULL, 'test', 1, '禁用');
INSERT INTO `t_systemuser` VALUES (7, '412543224@qq.com', '123', NULL, NULL, 3, '启用');
INSERT INTO `t_systemuser` VALUES (8, '11803060217', '123', '林海', 'rua', 3, '启用');

SET FOREIGN_KEY_CHECKS = 1;
