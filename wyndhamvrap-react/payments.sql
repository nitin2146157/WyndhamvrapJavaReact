/*
 Navicat Premium Data Transfer

 Source Server         : GCOM Mysql
 Source Server Type    : MySQL
 Source Server Version : 50718 (5.7.18-log)
 Source Host           : 10.59.41.16:3308
 Source Schema         : test_payments

 Target Server Type    : MySQL
 Target Server Version : 50718 (5.7.18-log)
 File Encoding         : 65001

 Date: 03/02/2025 15:18:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for card_type
-- ----------------------------
DROP TABLE IF EXISTS `card_type`;
CREATE TABLE `card_type`  (
  `card_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`card_type_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of card_type
-- ----------------------------
INSERT INTO `card_type` VALUES (1, '001', 'Visa', 'Visa');
INSERT INTO `card_type` VALUES (2, '002', 'Mastercard', 'Mastercard');
INSERT INTO `card_type` VALUES (3, '003', 'Amex', 'Amex');

-- ----------------------------
-- Table structure for cwa_payment
-- ----------------------------
DROP TABLE IF EXISTS `cwa_payment`;
CREATE TABLE `cwa_payment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email_address` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email_receipt_sent_count` int(1) NOT NULL DEFAULT 0,
  `owner_number` char(12) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `amount` double(7, 2) NOT NULL,
  `created_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_status` enum('waiting payment','payment successful','payment unsuccessful') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT 'waiting payment',
  `email_notification_sent_count` int(11) NULL DEFAULT 0,
  `email_notification_error_count` int(11) NULL DEFAULT 0,
  `payment_currency_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24636 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cwa_payment
-- ----------------------------


-- ----------------------------
-- Table structure for hbw_package_payment
-- ----------------------------
DROP TABLE IF EXISTS `hbw_package_payment`;
CREATE TABLE `hbw_package_payment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `package_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `destination` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `amount` double(7, 2) NOT NULL,
  `surcharge_amount` double(7, 2) NOT NULL,
  `total_amount` double(7, 2) NOT NULL,
  `email_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email_receipt_sent_count` int(255) NOT NULL DEFAULT 0,
  `created_ts` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modified_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `payment_status` enum('waiting payment','payment successful','payment unsuccessful') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'waiting payment',
  `first_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email_notification_sent_count` int(11) NULL DEFAULT 0,
  `email_notification_error_count` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2697 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hbw_package_payment
-- ----------------------------

-- ----------------------------
-- Table structure for nab_direct_post_transactions
-- ----------------------------
DROP TABLE IF EXISTS `nab_direct_post_transactions`;
CREATE TABLE `nab_direct_post_transactions`  (
  `id` varchar(23) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `source_system` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `source_system_pk` int(11) NOT NULL,
  `merchant_id` char(16) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `amount` double(7, 2) NOT NULL,
  `currency` char(3) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `reference` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `timestamp` char(14) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `fingerprint` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `payment_type` varchar(12) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `rescode` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `restext` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `txnid` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `settdate` varchar(8) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'Transaction set date',
  `sig` char(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'Signature generated and returned from NAB',
  `authid` int(10) UNSIGNED NULL DEFAULT NULL,
  `preauthid` char(6) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `pan` char(16) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'Truncated CC number returned from NAB',
  `expirydate` char(6) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'CC expiry date returned from NAB',
  `created_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_ts` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `merchant_id`(`merchant_id`) USING BTREE,
  INDEX `nab_created_ts`(`created_ts`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nab_direct_post_transactions
-- ----------------------------

-- ----------------------------
-- Table structure for paylink
-- ----------------------------
DROP TABLE IF EXISTS `paylink`;
CREATE TABLE `paylink`  (
  `id` int(11) NOT NULL,
  `full_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email_address` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email_receipt_sent_count` int(1) NOT NULL DEFAULT 0,
  `owner_number` char(12) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `amount` double(7, 2) NOT NULL,
  `created_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_status` enum('waiting payment','payment successful','payment unsuccessful') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT 'waiting payment',
  `email_notification_sent_count` int(11) NULL DEFAULT 0,
  `email_notification_error_count` int(11) NULL DEFAULT 0,
  `payment_currency_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paylink
-- ----------------------------

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `first_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dob` date NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `suburb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state_country` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `postcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `amount` double(7, 2) NULL DEFAULT NULL,
  `paid` tinyint(1) NULL DEFAULT 0,
  `nab_result_code` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sid` int(11) NULL DEFAULT NULL,
  `promo_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contact_consent` tinyint(1) NULL DEFAULT 0,
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ts_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`payment_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 785 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment
-- ----------------------------

-- ----------------------------
-- Table structure for payment_currency
-- ----------------------------
DROP TABLE IF EXISTS `payment_currency`;
CREATE TABLE `payment_currency`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment_currency
-- ----------------------------
INSERT INTO `payment_currency` VALUES (1, 'AUD');
INSERT INTO `payment_currency` VALUES (2, 'NZD');
INSERT INTO `payment_currency` VALUES (5, 'FJD');
INSERT INTO `payment_currency` VALUES (6, 'USD');

-- ----------------------------
-- Table structure for payment_gateway_config
-- ----------------------------
DROP TABLE IF EXISTS `payment_gateway_config`;
CREATE TABLE `payment_gateway_config`  (
  `payment_gateway_config_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_url` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `merchant_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `secret_key` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_key` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `profile_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `key_expiry_date` date NULL DEFAULT NULL,
  `payment_type_id` int(11) NOT NULL,
  `payment_currency_id` int(11) NULL DEFAULT NULL,
  `transaction_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`payment_gateway_config_id`) USING BTREE,
  UNIQUE INDEX `unique_payment_type_currency_id`(`payment_type_id`, `payment_currency_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment_gateway_config
-- ----------------------------
INSERT INTO `payment_gateway_config` VALUES (1, 'https://secureacceptance.cybersource.com/pay', 'wvr0017', '2fe71d4f887e4c74b62f24ed7405211587740b1ffa384bfa9183569390c87eb10b527da27bf44ebf82e8a48aa282dc7c63bc4cdd8c1e40cfa92db1eefbe9fd563d28050b8d094b3b9dc58d84b46c856c5d73bdcecc7e4df7b99778ba7aad911d1f3244a2a817481f86fcdbbba2eb471340249a41878e431d8ed6491997b9bc15', '21baa071da52368faba77fafb10df1f5', '1EB5FA10-DFDF-416A-B0BA-BB9470FF659A', NULL, 1, 1, 'sale');
INSERT INTO `payment_gateway_config` VALUES (14, 'https://secureacceptance.cybersource.com/pay', 'wvr0025', '8cb29d4254284274916eaa1d229bb059622bb109b9eb4c988016982866416eff05d3082c75a5414d98a6e36342f1c0da11677473797341529bb903fd9e5b487584d07f8bebae47c1a6e83059e199b395c36d5ce67cb141639cfe77e99ec9568cb3eac693052f45f3b89d0df8f5bd119760356e9605174f02ac8fe516662c27ca', 'f4de7fb29fbf3e99a7994cd2612cefb3', '587EF226-756D-4159-BAF2-CAB952A698F4', NULL, 2, 1, 'sale');
INSERT INTO `payment_gateway_config` VALUES (7, 'https://secureacceptance.cybersource.com/pay', 'wvo_1210', 'd9e2d9f53c1244f490cf856a230ede822c973e3c2ab24568b0a586bcbcc654a6f404b3e6e7bc4334a9946cbcfe1bd698be1233427da94bdd920e5b600fa3bc3d54c2611852f746cb9bf5d76e5b169ff822ac2aff64fe4b09bd82cc3308c8344dee2938a680d74b70bfb0b8b5c5c748aebea5c0a4178f4845ab0e8603efb5f4f6', 'db42caf9082c39e7aa76664f175a73fb', 'FDCE4D44-5355-4D24-B07D-5CA06EEF90C0', NULL, 1, 2, 'sale');
INSERT INTO `payment_gateway_config` VALUES (8, 'https://secureacceptance.cybersource.com/pay', 'wvr_1310', 'a2cd230346e14c4284894b765ee582a48cd17039a5ef4a66a084ffda48490e26462ae2bad9724809b5590453acc813af701f6c945ed34f10ba4f4396cfbeabc461f3a31d4fb241d2a65de353d919b18bf45086a1621e482dbcff2069405951f52efe0db0acf041ccaceadb50fe2a9c49535324fb60364c2884763a24ebe5d38c', '4d5e3589556a3f008e46c4aff7ceb9c1', '27B044DC-AA6D-4286-A0BB-343123571D61', NULL, 2, 2, 'sale');
INSERT INTO `payment_gateway_config` VALUES (10, 'https://secureacceptance.cybersource.com/pay', 'wynapp', 'a9ed94fa12f547a48604d56297d489ba02e87ed3c04d4aa79b8287fe8040cf6ba4afd983461a4a2aa25900498a9ffaaa12bd0523ac00463193a3d84d0ffc84533323b693b901458f912b843d67582e542dc0d0f4bc35482689ff331bf36313a914639db2208e41d996d908d10eb37e475885cf80af984dd092ac95f7913999e3', '24a6f586958c3ec39399887cb014e5fc', '60C14BBA-A791-4DEF-B5D4-19D2E4DC03BE', NULL, 3, 2, 'sale');
INSERT INTO `payment_gateway_config` VALUES (11, 'https://testsecureacceptance.cybersource.com/pay', '876543210', 'edb43f5608e241488228b13cf70fcbc21c19f75286714e049f04bae4a78b8f60c664d94b28ca4304a8351d3ceffd45a43ae29e7f0b8a4c1c8cd69185fc5876b9d49f159bb2cb48e5a6239fa1d02c0ba01d67ed28fb0a4cf2aa2e7662130b92be4ec9e6bbaddd49c281dee98bb72d6321f21396a967c146b691dfcc30405c11e2', 'a787d895eaef3090815202e13799c62d', '1CFAEBA7-5AD0-4059-8608-1451D3105E9F', NULL, 4, 1, 'sale');
INSERT INTO `payment_gateway_config` VALUES (12, 'https://testsecureacceptance.cybersource.com/pay', '876543210', 'edb43f5608e241488228b13cf70fcbc21c19f75286714e049f04bae4a78b8f60c664d94b28ca4304a8351d3ceffd45a43ae29e7f0b8a4c1c8cd69185fc5876b9d49f159bb2cb48e5a6239fa1d02c0ba01d67ed28fb0a4cf2aa2e7662130b92be4ec9e6bbaddd49c281dee98bb72d6321f21396a967c146b691dfcc30405c11e2', 'a787d895eaef3090815202e13799c62d', '1CFAEBA7-5AD0-4059-8608-1451D3105E9F', NULL, 4, 2, 'sale');
INSERT INTO `payment_gateway_config` VALUES (13, 'https://secureacceptance.cybersource.com/pay', 'cmp_minivacs_aud', 'd5d0e331d9154929ba7d736933cea8fcfc5e5c814ea84d26b6538d9774d4fac46142d5646c6540b3bb7c9631b01b6201c3b3925e60574f1692d02df13187c24b66fb1d83804049ef9097ee84714ef021c064f131c2294bd6a6acd29140dda6d72c7d1021f3a94580a9362f55e36df0937e3ee5fa9f544d32a4bf7bc08fb4e26f', '16d33d4a187c38ab8dbaa23e5cfe5cff', '851B5667-CA65-4CA0-9F11-88E799B44A31', NULL, 5, 1, 'sale');
INSERT INTO `payment_gateway_config` VALUES (16, 'https://testsecureacceptance.cybersource.com/token/create', 'ckn_test', 'be88dcabe314462584371b6c120a1b3f0446cf808bd34cecaf41e0d121fa6e417cf9247b7dd047e4984d2602dd1705dce8bf6b81c9a34d3c8438c1e342b51f6d493cdc64d5b847deb63862a407cad0890466e0162d744ef8ba4ccdc9f692d7bbaac83ba4ce724f619d8c672a4155c67e165778cdecbb46f9906bd3a5fc685c64', 'adbe35fbd24a396f86a088fbf56a7005', 'E306D66F-33DB-46EB-A2DA-51936564A895', NULL, 9, NULL, 'create_payment_token');
INSERT INTO `payment_gateway_config` VALUES (17, 'https://testsecureacceptance.cybersource.com/oneclick/pay', 'ckn_test', 'f4a8b6e10a1b4dbbaced8bdaa402a6357e965812fdda48a783cc226a11506b467d949ebb1b1c4982b6660ab3204fd4860c385b4702cf4be1ba2ad8d9c25688a714319e8848d0451b9e7dc43d3a9ea4af1eb6debdaf9d4a6792294591ac417cb241298e6fa2794a67aa78c52181c993097acb52edb2b3449faf0d9e6863e21476', 'a14de39ed1df39f59adf1b6d42cf5c2e', '121331D2-A8DE-460C-ABC4-C987E1C0A2F2', NULL, 9, NULL, 'sale');
INSERT INTO `payment_gateway_config` VALUES (18, 'https://testsecureacceptance.cybersource.com/token/create', 'express_rmbw', '998caad10bb84880a88ffcc9a66de197e76d1768c6ea4d7898bd27c991f4f630c8e77d67eea34ebe997d7e9f458a6af2a89a2a6f71494fb0b4a94b5a39f6ef4ded58f9f461e64f098730c49f5579831e17e72ca2f7bc41b187c2c49d8eef37a259ffa0c742564661b85633f58324af029818be1f7fd84b92859d8e452f84a084', '3268069752713f5191a4a8519e781150', '1EF39C7B-877E-4096-8F54-D06E05FD4D9F', NULL, 6, NULL, 'create_payment_token');
INSERT INTO `payment_gateway_config` VALUES (19, 'https://testsecureacceptance.cybersource.com/oneclick/pay', 'express_rmbw', '86b39beba1ef46b7a45dd851d010aacfd8ffc47c8e464ae1b6c2f10acf585985dde19b26e89c4fbf97f903eddc3669ee579da682b3284644b15479ce4027b3a11b93c9950056454b9e61b6fecf8841800050c3e3025041ffa63dbd3f0e4067922b164c7a10b8435b8ede3ea9cfa1609c989e5fd5080c4e149184dd6b9dea0960', 'c6ec7416cd8c359aaa281c080f18284b', '53D1B092-5EFD-4642-8083-A140B8E26FD6', NULL, 6, NULL, 'sale');
INSERT INTO `payment_gateway_config` VALUES (20, 'https://testsecureacceptance.cybersource.com/oneclick/pay', 'ckn_test', 'f4a8b6e10a1b4dbbaced8bdaa402a6357e965812fdda48a783cc226a11506b467d949ebb1b1c4982b6660ab3204fd4860c385b4702cf4be1ba2ad8d9c25688a714319e8848d0451b9e7dc43d3a9ea4af1eb6debdaf9d4a6792294591ac417cb241298e6fa2794a67aa78c52181c993097acb52edb2b3449faf0d9e6863e21476', 'a14de39ed1df39f59adf1b6d42cf5c2e', '121331D2-A8DE-460C-ABC4-C987E1C0A2F2', NULL, 8, 1, NULL);
INSERT INTO `payment_gateway_config` VALUES (38, 'https://secureacceptance.cybersource.com/oneclick/pay', 'express_club', 'de108c264d644d1fbd615548f683ef110578353b946444ce932b8cbe32d82de5db03dfbda6884de0a04f52dee635b2c9db7e19a637c44652962158866a18a8faed3d74277fb2474482139c260cb23d9235f417b385e94c48b4c0a9dac5645e77c67d51b2b2d941e9a7d03767e1c9d90aafdd7d4ba93e483aafc69b38f6d1e6e8', 'a623e096da6c30999a6058b269e63437', '711BE83E-8BB5-4E70-8F92-95950C19B4A2', NULL, 7, NULL, 'sale');
INSERT INTO `payment_gateway_config` VALUES (35, 'https://secureacceptance.cybersource.com/pay', 'wvo_1210', '1343c42992cb4a5e9b832804da844945c6cebdb849ed4ef18c526dd418f47af8095510b19a674a80b2b873c54e1dfcaf65b207d31dca4c7c98c0e83a351a77dd92e3900bbeb7491d821e4fa80ee649586ded473291cb45e39a3407c624b8abb6929527b823d241d9962c15250ff139bfb64ba36752074f26a5f11c0e71ba7ef6', '042ba10d8a703ffe969237589277a2e2', 'B621BF06-A6BA-4538-9AD9-36A622C47B63', NULL, 9, 2, 'sale');
INSERT INTO `payment_gateway_config` VALUES (36, 'https://secureacceptance.cybersource.com/pay', 'WVR0017', '2fe71d4f887e4c74b62f24ed7405211587740b1ffa384bfa9183569390c87eb10b527da27bf44ebf82e8a48aa282dc7c63bc4cdd8c1e40cfa92db1eefbe9fd563d28050b8d094b3b9dc58d84b46c856c5d73bdcecc7e4df7b99778ba7aad911d1f3244a2a817481f86fcdbbba2eb471340249a41878e431d8ed6491997b9bc15', '21baa071da52368faba77fafb10df1f5', '1EB5FA10-DFDF-416A-B0BA-BB9470FF659A', NULL, 9, 5, 'sale');
INSERT INTO `payment_gateway_config` VALUES (34, 'https://secureacceptance.cybersource.com/pay', 'wvr0017', 'b9c1b5db3f2e438e8f4d27d3acf6c769d21951eaf06c47a0b44225a2ad0bd37ca5a7977003fc4b158c2a23cf6b6cc01cc1c08d93382840e5ad2d9d3189e2dd8fbae59a9ca90d458ebad97de3464a1ad18869c3ba1f8e463b9ed77ba3f6e97d46b1cae29efba5448283a397ed236aebddedaa2ef8c27c4aa0880e8788eb647b31', '413e132aef15339ab972857e09842470', '2F31E16E-1DC9-4F33-B96B-CDBA9657BF02', NULL, 9, 1, 'sale');
INSERT INTO `payment_gateway_config` VALUES (37, 'https://secureacceptance.cybersource.com/token/create', 'express_club', 'de108c264d644d1fbd615548f683ef110578353b946444ce932b8cbe32d82de5db03dfbda6884de0a04f52dee635b2c9db7e19a637c44652962158866a18a8faed3d74277fb2474482139c260cb23d9235f417b385e94c48b4c0a9dac5645e77c67d51b2b2d941e9a7d03767e1c9d90aafdd7d4ba93e483aafc69b38f6d1e6e8', 'a623e096da6c30999a6058b269e63437', '711BE83E-8BB5-4E70-8F92-95950C19B4A2', NULL, 7, NULL, 'create_payment_token');
INSERT INTO `payment_gateway_config` VALUES (40, 'https://secureacceptance.cybersource.com/oneclick/pay', 'express_nzwbw', '314401f3e90d4a578df3a8d6a2d84d80a9be38f3f03b4950a08a47ece6f87df88adab137b5ba4948a6e2c2856c598f5a366bd1a67e944ebf900dc820ad90c7689f5787f4cb2643b9aa8fbbcaa060c9ffc2a0e838aaa04c62bd674aaae22e268810a821709c724ba5a0ae6c9aeb096f1339ae27f74c4c4cc8b78388a7680dc63d', 'ca660540f7343a6991adce89a3fceac0', '7AD01E53-9E82-49B5-B920-FE6A0329F4C8', NULL, 7, NULL, 'sale');
INSERT INTO `payment_gateway_config` VALUES (42, 'https://secureacceptance.cybersource.com/oneclick/pay', 'express_nzwvr', '80db1bc8a90c46108357f346ad8796af2be52c6aba954835996b8f50c9e775b09c139f1585c44a9588c4b65a9a06ca25874b580557ae4ed5bca625fa695a9b62677f7c14147c40268a48be0a5665fca54c26b63cfbbd4cd588ab254428aadd3c7ee0a2784b124726a2ec1981feecdc4a4eb05f7ec24b46fbbf7afb624edb05a4', '2e742fcc483c3d648f585cd58c53423b', 'D1B61AA1-F231-4F73-81F6-0B4827F3F068', NULL, 7, NULL, 'sale');
INSERT INTO `payment_gateway_config` VALUES (44, 'https://secureacceptance.cybersource.com/pay', 'W2WFIN', 'aa37d9d6db204643a8280f79f3b1daa21bce4fda9640429bb85c951f1c586910ce825eef52fa4a60975cd9f98c6beab118c5d02862ff4e898155739d26631bd70665d1607dfa4cf8a15b27201f1ac39d8f9d1d11dca54d64ae4a58a9d90ea14f540884c0c68245a19979975a64f543863eeaae11b23a42b0bb15c31dd4bafab4', '668127b8be223ffaacf77b506adde77b', '07DA7AC4-6E6A-4663-899A-B7E01331F2E1', NULL, 10, 2, 'sale');
INSERT INTO `payment_gateway_config` VALUES (43, 'https://secureacceptance.cybersource.com/pay', 'WVR0012', '5c375b3736664ebeb5687923798c049ab28f5233f35c4ce0b4788a38e416bd65156d64c96dc142e0be18e72784de0f26363745b2b142454a821e0518a7791e367b65f4629eff458fa5cfa692f3d79295d225ecd6e00743da9204434b9922d603da5ae7043b6e4396b456410d627b9ef466fe2bc9a3cb4c79887f1cadc818bc36', '35663b2e004831a4b78669c25f2c4840', 'FDBBFBC5-4136-42FA-8AFA-FF3AC3B8CCF1', NULL, 10, 1, 'sale');
INSERT INTO `payment_gateway_config` VALUES (45, 'https://secureacceptance.cybersource.com/pay', 'WVR0051', '2df28cd1570243a09b3f7df2ddefd1427f8e6e228dd74ee1819825940521d78b7630ed60b95e44f7afb3aaa73f76c7179a4b4c4f68c24ec696f6f85510b62fc3938eaa5f928f456aa962a1539b07e345f47f42757bd54981946cb7c60059684a09cfb3d935ea485d980323a498a7cfee69f9189c467c4e7ea942f4b9dc58d251', 'dbbe87b5b6233e52baaf76930a4ec886', 'E35F22DC-40F1-4A69-A349-F04E645E7C5D', NULL, 11, 1, 'sale');
INSERT INTO `payment_gateway_config` VALUES (46, 'https://secureacceptance.cybersource.com/pay', 'wvr0053', 'e30e3b8f066045cea1b48c1334168f3233bec60a27074f0bbc687dd9194b28285257577296c443de8b18ecece25714047e6f9fdd1def4b8984c85e71308c93a3711941b8269049468ef02a31cb230606adcc1d70a10942bb98ef06faa2facb2f729eaa7e1a2d41ad917cf2cc97013efc191f6a7f42464da687ca126bb6f7a828', '5fe981d63d0236ff932b81493c9bb551', '377B6FEB-C62E-4E58-9C9D-B126F7BFBA85', NULL, 12, 1, 'sale');
INSERT INTO `payment_gateway_config` VALUES (47, 'https://secureacceptance.cybersource.com/pay', 'wvr0053', 'c4af993abcbd4458bfd1d55450633674bbf194b690e44a9687148d7e10ce03234e5149d1bf6c42f3925ab3394d04662027bfc70cf4604d9d8dd278fc3cc0a2bbad8f5eddf402462fad2ae68560f61fd92cd1e507acc04271858f6c00eba1b574beeae48cf4684654a333880bb0da386af7fc8095a83c4d2aab07fed13d2badf7', 'c1957135400438e39fd96db640b3538a', '1E2BA30E-7406-4816-A486-7FCDA74BDDC1', NULL, 12, 2, 'sale');
INSERT INTO `payment_gateway_config` VALUES (48, 'https://secureacceptance.cybersource.com/pay', 'WVR0016', '0c2797b62ad945c0a00d16122d623b9d071608b5824941ad8262c68c344c5a3dad75f5ed2d5c4d7d92685ebd27c8811094154ae369094ddbb60862357db4869093d0865eb0c9478c993bba913edffb450e7c5f9704664c4bb7fbe3e0276786e60a11d699d8104ad0abeb9d9674789da6581bdb59e9e24fb099361822a424bb2d', 'b15ec606c84b3ea499130f126d793229', '0C969A50-A7A7-4C25-A80E-20668C773B28', NULL, 13, 1, 'sale');
INSERT INTO `payment_gateway_config` VALUES (49, 'https://secureacceptance.cybersource.com/pay', 'wvr_1310', '643a149d68664f018163f2507564017cd61ad589bb8445178ecec41954a53c6d3b3f3fb2b9074cb0801dca97af5e54f46ebc0d1373734f0688638b81e3cafada96d6f73e542d4ac49117242b020d8f1ede74f3a67da64e698f965246a29660dde2e2101dc2064d07a616e39fb7b4b725202d8a7b70ca4cd8b19a373f70493bef', 'f5bb47d5f9be3d6083a97ed5319d19b7', 'DC73A76D-B0E6-4BA2-B104-A0602E840E32', NULL, 13, 2, 'sale');
INSERT INTO `payment_gateway_config` VALUES (50, 'https://secureacceptance.cybersource.com/pay', 'Wyndham_rec', 'c4504c97fabe4a6782cff1793563fc624be9e48b6f8d4edea31173ae2e6fc19ee919681f98454114a3e17f85487b589de91941b95a784f3d958b0b1bf3f97fc1eaa8f915e67346bab1638ce29152c934eece27acc1e44a14b1fed81afd28e0caaf1dac1e5b7d484389cee30b092c5622d2135c5bb206416a9b428cfa5a6461c8', '3aa3a6193750372ea2a8a68541a8b677', '9350A403-E7B9-4437-8D59-89190F119528', '2026-05-08', 14, 1, 'sale');
INSERT INTO `payment_gateway_config` VALUES (51, 'https://secureacceptance.cybersource.com/pay', 'Wyndham_rec', 'c4504c97fabe4a6782cff1793563fc624be9e48b6f8d4edea31173ae2e6fc19ee919681f98454114a3e17f85487b589de91941b95a784f3d958b0b1bf3f97fc1eaa8f915e67346bab1638ce29152c934eece27acc1e44a14b1fed81afd28e0caaf1dac1e5b7d484389cee30b092c5622d2135c5bb206416a9b428cfa5a6461c8', '3aa3a6193750372ea2a8a68541a8b677', '9350A403-E7B9-4437-8D59-89190F119528', '2026-05-08', 14, 2, 'sale');
INSERT INTO `payment_gateway_config` VALUES (52, 'https://secureacceptance.cybersource.com/pay', 'Wyndham_rec', 'c4504c97fabe4a6782cff1793563fc624be9e48b6f8d4edea31173ae2e6fc19ee919681f98454114a3e17f85487b589de91941b95a784f3d958b0b1bf3f97fc1eaa8f915e67346bab1638ce29152c934eece27acc1e44a14b1fed81afd28e0caaf1dac1e5b7d484389cee30b092c5622d2135c5bb206416a9b428cfa5a6461c8', '3aa3a6193750372ea2a8a68541a8b677', '9350A403-E7B9-4437-8D59-89190F119528', '2026-05-08', 14, 6, 'sale');
INSERT INTO `payment_gateway_config` VALUES (53, 'https://secureacceptance.cybersource.com/pay', 'Wyndham_rec', 'c4504c97fabe4a6782cff1793563fc624be9e48b6f8d4edea31173ae2e6fc19ee919681f98454114a3e17f85487b589de91941b95a784f3d958b0b1bf3f97fc1eaa8f915e67346bab1638ce29152c934eece27acc1e44a14b1fed81afd28e0caaf1dac1e5b7d484389cee30b092c5622d2135c5bb206416a9b428cfa5a6461c8', '3aa3a6193750372ea2a8a68541a8b677', '9350A403-E7B9-4437-8D59-89190F119528', '2026-05-08', 14, 5, 'sale');
INSERT INTO `payment_gateway_config` VALUES (54, 'https://testsecureacceptance.cybersource.com/oneclick/pay', 'ckn_test', '8607f012d70b4fdabae78aa87f180df8467de292e49944e483203bbc80689ed03edae4a99ced4504b66cedc0976f201bbd1fbacb7f8a451f8ecfb8eafb2fc023eaa19a013297428fbbb4efbfd81c008113b950829c3342c1bdd51e4a29d8a5d12900c3208f91417eac84673f095d4da876ce812db5324748a3c9236d3d5807d8', 'a84b72c8aa9f3e409b12c9e76ed39984', 'E306D66F-33DB-46EB-A2DA-51936564A895', NULL, 18, 1, 'sale');
INSERT INTO `payment_gateway_config` VALUES (55, 'https://testsecureacceptance.cybersource.com/oneclick/pay', 'ckn_test', '8607f012d70b4fdabae78aa87f180df8467de292e49944e483203bbc80689ed03edae4a99ced4504b66cedc0976f201bbd1fbacb7f8a451f8ecfb8eafb2fc023eaa19a013297428fbbb4efbfd81c008113b950829c3342c1bdd51e4a29d8a5d12900c3208f91417eac84673f095d4da876ce812db5324748a3c9236d3d5807d8', 'a84b72c8aa9f3e409b12c9e76ed39984', 'E306D66F-33DB-46EB-A2DA-51936564A895', NULL, 18, 2, 'sale');
INSERT INTO `payment_gateway_config` VALUES (56, 'https://secureacceptance.cybersource.com/pay', 'Wyndham_rec', '3c28ab5bd845453c99173ee208ee36cb4decd62ce9c944c6ae76ccd84b619a3df5701ff787bb4863808cd29e4fff35af1843356326ca47c89000880c1d75890a05042fbe990e4244bf6e491c9fdf0a37f5d71c9c7f524794af26fbf9ce3d7594a7c022c367f3446880313e715a384574a2f498a9be324b0ca0e83c310b60016f', '3fb9e53636a33eb6b3abbaa88b750195', '38B64B8B-9507-47A1-B3F0-EB9DEC28A7A9', '2026-05-08', 19, 5, 'sale');
INSERT INTO `payment_gateway_config` VALUES (57, 'https://testsecureacceptance.cybersource.com/oneclick/pay', 'ckn_test', '14b086f1aa3d4a68bbf76f3220e289324088502a0e624f3db9dedd2f81cd6b4ac5e420fcf6074a43a92acb3e3faf4a61ad1846df565b4d70afc65b1265f338dee29fb82ace2c45d8b4636068cb5eb0d7e4054ee96cd048c6b595f36603c16d0792f1ab3adb8b4be09032aa16478b5a9f706a0230f757430aaa0c4082cb8f0ed5', '18fcf3fb7dd538a294f90e9f0b39b7f7', 'C9175C86-A1A2-4CC8-A388-3DCE55A01282', '2026-05-08', 19, 1, 'sale');
INSERT INTO `payment_gateway_config` VALUES (58, 'https://testsecureacceptance.cybersource.com/oneclick/pay', 'wyndham_rec', '350f060eff074587ad75dc3075afbdec86fa8ec7ea4b4fc4b4e894f5e41a9d248db2c517ca7244dea70429df7b22c35f556ef3adff10450783318379a034c27be71901431bbb4f01b0d0602c6c69221f9c196b08847a4356a6ed08a0e6d34284229042619ffd422ab063aa10389aa11910200720c9f848b784ebec36933b9470', 'a183387093c33b369f9a72bf7c823cda', '2E9F538E-F37E-4D53-8553-45EB2E2A73C3', '2027-01-16', 19, 6, 'sale');

-- ----------------------------
-- Table structure for payment_gateway_config_stage
-- ----------------------------
DROP TABLE IF EXISTS `payment_gateway_config_stage`;
CREATE TABLE `payment_gateway_config_stage`  (
  `payment_gateway_config_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_url` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `merchant_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `secret_key` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_key` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `profile_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_type_id` int(11) NOT NULL,
  `payment_currency_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`payment_gateway_config_id`) USING BTREE,
  UNIQUE INDEX `unique_payment_type_currency_id`(`payment_type_id`, `payment_currency_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment_gateway_config_stage
-- ----------------------------
INSERT INTO `payment_gateway_config_stage` VALUES (1, 'https://testsecureacceptance.cybersource.com/silent/pay', '876543210', '939211f05d264a408e0c0dcdba17892c9736121cdede405686db1807bdddc25053ad28bc6790404d963df3ecf8a52d518ef307cd374d4af6a344719eb12f0377d787d3fef8624226bba94878c3dd70b531fed4bc800843cab0c498d8104382da26a20e89e8264d6b93beb202cc368e1b318c9a8dce6541669eec90d56064f119', '1c361efac67a32fea535d02b8ca87958', '19809F6A-4A40-4779-9DC0-71CEF70197D8', 1, 1);
INSERT INTO `payment_gateway_config_stage` VALUES (4, 'https://testsecureacceptance.cybersource.com/silent/pay', '876543210', '7f3545a7072a424f9febea140404eaa617dcf72d688f421fa9de39a7c4f87141c999bcca24cb46438d7b00425083c9d60e98735222844b36a71fb6582181321d48b1a33a95ba44f98958ef6adbb80de66b7e05f406f645fabe7a865fa46dcf8d119954e09932422f81e649aa7f1e0fcdcdff9891135b45e4a3133d1b4907fb84', '0025b010a05533c6b723f1ca7373c0f2', 'F64EA5F3-BBD7-40C0-BEB1-F08AA11FB8EA', 2, 1);
INSERT INTO `payment_gateway_config_stage` VALUES (7, 'https://testsecureacceptance.cybersource.com/pay', 'wvo_1210', '1cb4ff28227f4461942292b7c9d591aad00fcc62b45848c8befd6fb2d2d447e0ddde5bb822f0405e8bfa910ba3b2eb20619a3d49be0a487dbff3d5958c66e656362f57b512ff4f73b0c87418cb5be07297bb005837f24cdabebceedd999cf6121e58a003529e4a98afcf5d21f6b000b02150f4379ce94ad98af54b669ee788b6', '2bfb28b922733acdb5ba6ae0f5d1838a', '2BB546C8-7018-48D7-A244-B26D243261CD', 1, 2);
INSERT INTO `payment_gateway_config_stage` VALUES (8, 'https://testsecureacceptance.cybersource.com/pay', 'wvr_1310', 'b98004d2cc1c4d5bb467746a19eeaa24f52c6ac4fe524dd4a90741b5559090a8f51019548f404194b70e149c6303770dff54d4fd400a4240838d011a22a08479958159e34a634ac5b62e761e4c04156cd5c623096f994af487f209df710f175645c8f092dd0f4fa6969d2f0c8424c789c95bc12abaf646219b6c22d00e676f04', '49948dc9b3a1355d9943e9476f820a22', 'C9EA452D-158F-496D-B0ED-C65A4AD1F3E6', 2, 2);

-- ----------------------------
-- Table structure for payment_test
-- ----------------------------
DROP TABLE IF EXISTS `payment_test`;
CREATE TABLE `payment_test`  (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `first_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dob` date NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `suburb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state_country` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `postcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paid` tinyint(1) NULL DEFAULT 0,
  `nab_result_code` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sid` int(11) NULL DEFAULT NULL,
  `promo_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contact_consent` tinyint(1) NULL DEFAULT 0,
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ts_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`payment_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 131 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment_test
-- ----------------------------

-- ----------------------------
-- Table structure for payment_type
-- ----------------------------
DROP TABLE IF EXISTS `payment_type`;
CREATE TABLE `payment_type`  (
  `payment_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`payment_type_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 69 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment_type
-- ----------------------------
INSERT INTO `payment_type` VALUES (1, 'WMSP-Finance', 'WMSP-Finance');
INSERT INTO `payment_type` VALUES (2, 'WMSP-Levies', 'WMSP-Levies');
INSERT INTO `payment_type` VALUES (3, 'WMSP-Deposits', 'WMSP-Deposits');
INSERT INTO `payment_type` VALUES (4, 'WMSP-Discovery', 'WMSP-Discovery');
INSERT INTO `payment_type` VALUES (5, 'WMSP-Minivacs', 'WMSP-Minivacs');
INSERT INTO `payment_type` VALUES (6, 'Express-Checkin-Token1', 'Express-Checkin-Token1');
INSERT INTO `payment_type` VALUES (7, 'Express-Checkin', 'Express-Checkin');
INSERT INTO `payment_type` VALUES (8, 'Paylink', 'Paylink');
INSERT INTO `payment_type` VALUES (9, 'Paylink-Consumer-Finance-Finance', 'Paylink-Consumer-Finance-Finance');
INSERT INTO `payment_type` VALUES (10, 'Paylink-Consumer-Finance-Discovery', 'Paylink-Consumer-Finance-Discovery');
INSERT INTO `payment_type` VALUES (11, 'Paylink-Consumer-Finance-Transfer', 'Paylink-Consumer-Finance-Transfer');
INSERT INTO `payment_type` VALUES (12, 'Paylink-Consumer-Finance-Deposit', 'Paylink-Consumer-Finance-Deposit');
INSERT INTO `payment_type` VALUES (13, 'Paylink-Owner-Services', 'Paylink-Owner-Services');
INSERT INTO `payment_type` VALUES (14, 'Paylink-Marketing', 'Paylink-Marketing');
INSERT INTO `payment_type` VALUES (15, 'Express-Checkin-nzwbw', 'Express-Checkin-nzwbw');
INSERT INTO `payment_type` VALUES (16, 'Express-Checkin-nzwvr', 'Express-Checkin-nzwvr');
INSERT INTO `payment_type` VALUES (17, 'Paylink-Consumer-Finance-Levies', 'Paylink-Consumer-Finance-Levies');
INSERT INTO `payment_type` VALUES (18, 'Paylink-Salesforce', 'Paylink-Salesforce');
INSERT INTO `payment_type` VALUES (19, 'CWA-Payments', 'CWA-Payments');

-- ----------------------------
-- Table structure for transaction_source
-- ----------------------------
DROP TABLE IF EXISTS `transaction_source`;
CREATE TABLE `transaction_source`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `system` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of transaction_source
-- ----------------------------
INSERT INTO `transaction_source` VALUES (2, 'Privileges', 'Membership');

-- ----------------------------
-- Table structure for wmsp_deposits_payment
-- ----------------------------
DROP TABLE IF EXISTS `wmsp_deposits_payment`;
CREATE TABLE `wmsp_deposits_payment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email_address` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email_receipt_sent_count` int(1) NOT NULL DEFAULT 0,
  `reference_number` char(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `amount` double(7, 2) NOT NULL,
  `created_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_status` enum('waiting payment','payment successful','payment unsuccessful') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT 'waiting payment',
  `email_notification_sent_count` int(11) NULL DEFAULT 0,
  `email_notification_error_count` int(11) NULL DEFAULT 0,
  `payment_currency_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wmsp_deposits_payment
-- ----------------------------

-- ----------------------------
-- Table structure for wmsp_discovery_payment
-- ----------------------------
DROP TABLE IF EXISTS `wmsp_discovery_payment`;
CREATE TABLE `wmsp_discovery_payment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email_address` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email_receipt_sent_count` int(1) NOT NULL DEFAULT 0,
  `owner_number` char(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `amount` double(7, 2) NOT NULL,
  `created_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_status` enum('waiting payment','payment successful','payment unsuccessful') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT 'waiting payment',
  `email_notification_sent_count` int(11) NULL DEFAULT 0,
  `email_notification_error_count` int(11) NULL DEFAULT 0,
  `payment_currency_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15749 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wmsp_discovery_payment
-- ----------------------------

-- ----------------------------
-- Table structure for wmsp_express_payment
-- ----------------------------
DROP TABLE IF EXISTS `wmsp_express_payment`;
CREATE TABLE `wmsp_express_payment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email_address` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email_receipt_sent_count` int(1) NOT NULL DEFAULT 0,
  `owner_number` char(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `amount` double(7, 2) NOT NULL,
  `created_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_status` enum('waiting payment','payment successful','payment unsuccessful') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT 'waiting payment',
  `email_notification_sent_count` int(11) NULL DEFAULT 0,
  `email_notification_error_count` int(11) NULL DEFAULT 0,
  `payment_currency_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 244 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wmsp_express_payment
-- ----------------------------

-- ----------------------------
-- Table structure for wmsp_finance_payment
-- ----------------------------
DROP TABLE IF EXISTS `wmsp_finance_payment`;
CREATE TABLE `wmsp_finance_payment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email_address` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email_receipt_sent_count` int(1) NOT NULL DEFAULT 0,
  `contract_number` char(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `amount` double(7, 2) NOT NULL,
  `created_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_status` enum('waiting payment','payment successful','payment unsuccessful') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT 'waiting payment',
  `email_notification_sent_count` int(11) NULL DEFAULT 0,
  `email_notification_error_count` int(11) NULL DEFAULT 0,
  `payment_currency_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15736 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wmsp_finance_payment
-- ----------------------------

-- ----------------------------
-- Table structure for wmsp_levies_payment
-- ----------------------------
DROP TABLE IF EXISTS `wmsp_levies_payment`;
CREATE TABLE `wmsp_levies_payment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email_address` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email_receipt_sent_count` int(1) NOT NULL DEFAULT 0,
  `owner_number` char(12) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `amount` double(7, 2) NOT NULL,
  `created_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_status` enum('waiting payment','payment successful','payment unsuccessful') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT 'waiting payment',
  `email_notification_sent_count` int(11) NULL DEFAULT 0,
  `email_notification_error_count` int(11) NULL DEFAULT 0,
  `payment_currency_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24611 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wmsp_levies_payment
-- ----------------------------

-- ----------------------------
-- Table structure for wmsp_minivacs_payment
-- ----------------------------
DROP TABLE IF EXISTS `wmsp_minivacs_payment`;
CREATE TABLE `wmsp_minivacs_payment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email_receipt_sent_count` int(1) NOT NULL DEFAULT 0,
  `amount` double(7, 2) NOT NULL,
  `created_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_status` enum('waiting payment','payment successful','payment unsuccessful') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT 'waiting payment',
  `email_notification_sent_count` int(11) NULL DEFAULT 0,
  `email_notification_error_count` int(11) NULL DEFAULT 0,
  `payment_currency_id` int(11) NULL DEFAULT NULL,
  `reference_number` char(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 144 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wmsp_minivacs_payment
-- ----------------------------


-- ----------------------------
-- Table structure for wmsp_privileges
-- ----------------------------
DROP TABLE IF EXISTS `wmsp_privileges`;
CREATE TABLE `wmsp_privileges`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `amount` decimal(11, 2) NOT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `added_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_ts` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4422 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wmsp_privileges
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
