/*
 Navicat Premium Data Transfer

 Source Server         : mysql1
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : ordersys

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 13/07/2024 17:14:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins`  (
  `AdminID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`AdminID`) USING BTREE,
  UNIQUE INDEX `Username`(`Username`) USING BTREE,
  UNIQUE INDEX `Email`(`Email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES (1, 'root', '123456', 'hubu@edu.com');

-- ----------------------------
-- Table structure for dishes
-- ----------------------------
DROP TABLE IF EXISTS `dishes`;
CREATE TABLE `dishes`  (
  `DishID` int(11) NOT NULL AUTO_INCREMENT,
  `MerchantID` int(11) NULL DEFAULT NULL,
  `DishName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Price` decimal(10, 2) NOT NULL,
  `ImageURL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`DishID`) USING BTREE,
  INDEX `MerchantID`(`MerchantID`) USING BTREE,
  CONSTRAINT `dishes_ibfk_1` FOREIGN KEY (`MerchantID`) REFERENCES `merchants` (`MerchantID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dishes
-- ----------------------------
INSERT INTO `dishes` VALUES (1, 1, '意式披萨', '意式肉酱风味', 38.00, '/images/dishs/pizza.jpg');
INSERT INTO `dishes` VALUES (2, 1, '战斧牛排', '五分熟', 68.00, '/images/dishs/niupai.jpg');
INSERT INTO `dishes` VALUES (3, 1, '靓靓蒸虾', '蒜蓉口味', 128.00, '/images/dishs/xia.jpg');
INSERT INTO `dishes` VALUES (4, 1, '三文鱼', '送芥末酱', 88.00, '/images/dishs/sanwenyu.jpg');
INSERT INTO `dishes` VALUES (5, 1, '美味小蛋糕', '动物奶油', 188.00, '/images/dishs/cake.jpg');
INSERT INTO `dishes` VALUES (6, 1, '健康小汉堡', '绿色无害', 38.00, '/images/dishs/ham.jpg');
INSERT INTO `dishes` VALUES (7, 1, '樱桃沙拉', '解腻小甜点', 18.00, '/images/dishs/cherry.jpg');
INSERT INTO `dishes` VALUES (8, 1, '葡萄味小果汁', '酒好不要贪杯哦', 188.00, '/images/dishs/juice.jpg');
INSERT INTO `dishes` VALUES (9, 1, '波士顿龙瞎', '晒勾9981天', 288.00, '/images/dishs/boston.jpg');
INSERT INTO `dishes` VALUES (10, 1, '无敌小热狗', '美式风味', 28.00, '/images/dishs/hotdog.jpg');
INSERT INTO `dishes` VALUES (11, 1, '包子', '大包子', 1.00, '/images/dishs/baozi.jpg');

-- ----------------------------
-- Table structure for merchants
-- ----------------------------
DROP TABLE IF EXISTS `merchants`;
CREATE TABLE `merchants`  (
  `MerchantID` int(11) NOT NULL AUTO_INCREMENT,
  `MerchantName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`MerchantID`) USING BTREE,
  UNIQUE INDEX `MerchantName`(`MerchantName`) USING BTREE,
  UNIQUE INDEX `Email`(`Email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of merchants
-- ----------------------------
INSERT INTO `merchants` VALUES (1, '西餐厅', '123456', 'hubuResterant@hubu.com', '94520', '湖北大学西餐厅');

-- ----------------------------
-- Table structure for orderdetails
-- ----------------------------
DROP TABLE IF EXISTS `orderdetails`;
CREATE TABLE `orderdetails`  (
  `OrderDetailID` int(11) NOT NULL AUTO_INCREMENT,
  `OrderID` int(11) NULL DEFAULT NULL,
  `DishID` int(11) NULL DEFAULT NULL,
  `Quantity` int(11) NOT NULL,
  `Price` decimal(10, 2) NOT NULL,
  PRIMARY KEY (`OrderDetailID`) USING BTREE,
  INDEX `OrderID`(`OrderID`) USING BTREE,
  INDEX `DishID`(`DishID`) USING BTREE,
  CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`DishID`) REFERENCES `dishes` (`DishID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderdetails
-- ----------------------------
INSERT INTO `orderdetails` VALUES (4, 1, 1, 1, 38.00);
INSERT INTO `orderdetails` VALUES (5, 11, 2, 1, 58.00);
INSERT INTO `orderdetails` VALUES (6, 11, 3, 1, 128.00);
INSERT INTO `orderdetails` VALUES (9, 13, 2, 1, 58.00);
INSERT INTO `orderdetails` VALUES (10, 13, 3, 1, 128.00);
INSERT INTO `orderdetails` VALUES (14, 17, 2, 1, 58.00);
INSERT INTO `orderdetails` VALUES (15, 17, 3, 1, 128.00);
INSERT INTO `orderdetails` VALUES (19, 19, 10, 1, 28.00);
INSERT INTO `orderdetails` VALUES (20, 20, 2, 1, 68.00);
INSERT INTO `orderdetails` VALUES (21, 20, 9, 3, 288.00);
INSERT INTO `orderdetails` VALUES (22, 20, 11, 1, 1.00);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `OrderID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NULL DEFAULT NULL,
  `MerchantID` int(11) NULL DEFAULT NULL,
  `Status` enum('placed','taken','delivery','completed','rated') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TotalPrice` decimal(10, 2) NOT NULL,
  `CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`OrderID`) USING BTREE,
  INDEX `UserID`(`UserID`) USING BTREE,
  INDEX `MerchantID`(`MerchantID`) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`MerchantID`) REFERENCES `merchants` (`MerchantID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 1, 1, 'rated', 368.00, '2024-07-11 11:33:35');
INSERT INTO `orders` VALUES (2, 1, 1, 'rated', 288.00, '2024-07-11 15:49:32');
INSERT INTO `orders` VALUES (3, 1, 1, 'rated', 188.00, '2024-07-11 15:49:47');
INSERT INTO `orders` VALUES (11, 1, 1, 'delivery', 224.00, '2024-07-12 11:19:17');
INSERT INTO `orders` VALUES (13, 1, 1, 'taken', 186.00, '2024-07-12 11:36:35');
INSERT INTO `orders` VALUES (17, 3, 1, 'placed', 186.00, '2024-07-12 15:18:47');
INSERT INTO `orders` VALUES (19, 1, 1, 'completed', 28.00, '2024-07-13 15:30:24');
INSERT INTO `orders` VALUES (20, 1, 1, 'placed', 933.00, '2024-07-13 16:26:15');

-- ----------------------------
-- Table structure for reviews
-- ----------------------------
DROP TABLE IF EXISTS `reviews`;
CREATE TABLE `reviews`  (
  `ReviewID` int(11) NOT NULL AUTO_INCREMENT,
  `OrderID` int(11) NULL DEFAULT NULL,
  `UserID` int(11) NULL DEFAULT NULL,
  `Rating` int(11) NULL DEFAULT NULL,
  `Comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CreatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ReviewID`) USING BTREE,
  INDEX `OrderID`(`OrderID`) USING BTREE,
  INDEX `UserID`(`UserID`) USING BTREE,
  CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reviews
-- ----------------------------
INSERT INTO `reviews` VALUES (1, 1, 1, 5, '美味', '2024-07-11 17:45:02');
INSERT INTO `reviews` VALUES (2, 2, 1, 4, '还可以吧', '2024-07-11 17:44:30');
INSERT INTO `reviews` VALUES (7, 3, 1, 5, '非常的好吃！', '2024-07-11 21:14:36');
INSERT INTO `reviews` VALUES (10, 19, 1, 3, '味道一般般，太辣了。', '2024-07-13 16:59:12');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`UserID`) USING BTREE,
  UNIQUE INDEX `Username`(`Username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, '云天泽', '123456', '13094107610', '湖北大学南门');
INSERT INTO `users` VALUES (2, '王钰龙', '123456', '110', '湖北大学北门');
INSERT INTO `users` VALUES (3, '张利鹏', '123456', '119', '湖北大学西门');
INSERT INTO `users` VALUES (4, '姜佳伟', '123456', '18888', '湖北大学东门');
INSERT INTO `users` VALUES (5, 'user', '123456', '1898989', '湖北大学双创大楼');

SET FOREIGN_KEY_CHECKS = 1;
