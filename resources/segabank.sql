-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le :  ven. 11 oct. 2019 à 12:44
-- Version du serveur :  5.7.19
-- Version de PHP :  7.1.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `segabank`
--
CREATE DATABASE IF NOT EXISTS `segabank` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `segabank`;

-- --------------------------------------------------------

--
-- Structure de la table `agence`
--

CREATE TABLE `agence` (
  `id` int(11) NOT NULL,
  `code` int(11) NOT NULL,
  `adresse` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `agence`
--

INSERT INTO `agence` (`id`, `code`, `adresse`) VALUES
(1, 555, '16 Boulevard Général de Gaulle'),
(2, 463, '2 rue du Vent d\'Ouest');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `id` int(11) NOT NULL,
  `solde` double NOT NULL,
  `agence` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`id`, `solde`, `agence`) VALUES
(13, 762, 1),
(15, 200, 1),
(16, 100, 2),
(17, 100, 1),
(18, 570, 2),
(19, 1000, 1);

-- --------------------------------------------------------

--
-- Structure de la table `compteepargne`
--

CREATE TABLE `compteepargne` (
  `id` int(11) NOT NULL,
  `tauxInteret` double NOT NULL,
  `compteId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `compteepargne`
--

INSERT INTO `compteepargne` (`id`, `tauxInteret`, `compteId`) VALUES
(11, 4, 17),
(12, 1, 19);

-- --------------------------------------------------------

--
-- Structure de la table `comptepayant`
--

CREATE TABLE `comptepayant` (
  `id` int(11) NOT NULL,
  `tauxOperation` double NOT NULL,
  `compteId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `comptepayant`
--

INSERT INTO `comptepayant` (`id`, `tauxOperation`, `compteId`) VALUES
(11, 5, 15);

-- --------------------------------------------------------

--
-- Structure de la table `comptesimple`
--

CREATE TABLE `comptesimple` (
  `id` int(11) NOT NULL,
  `decouvert` double NOT NULL,
  `compteId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `comptesimple`
--

INSERT INTO `comptesimple` (`id`, `decouvert`, `compteId`) VALUES
(13, -14, 13),
(14, 99, 16),
(15, 4, 18);

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

CREATE TABLE `operation` (
  `id` int(11) NOT NULL,
  `montant` double NOT NULL,
  `transaction` varchar(255) DEFAULT NULL,
  `compteId` int(11) DEFAULT NULL,
  `agenceId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `operation`
--

INSERT INTO `operation` (`id`, `montant`, `transaction`, `compteId`, `agenceId`) VALUES
(1, 100, 'Retrait', 13, 1),
(2, 1, 'Virement', 17, 1),
(3, 38, 'Retrait', 13, 1),
(4, 8, 'Virement', 18, 2),
(5, 658, 'Retrait', 19, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `agence`
--
ALTER TABLE `agence`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `agence_id` (`agence`);

--
-- Index pour la table `compteepargne`
--
ALTER TABLE `compteepargne`
  ADD PRIMARY KEY (`id`),
  ADD KEY `compte_id_epargne` (`compteId`);

--
-- Index pour la table `comptepayant`
--
ALTER TABLE `comptepayant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `compte_id_payant` (`compteId`);

--
-- Index pour la table `comptesimple`
--
ALTER TABLE `comptesimple`
  ADD PRIMARY KEY (`id`),
  ADD KEY `compte_id_simple` (`compteId`);

--
-- Index pour la table `operation`
--
ALTER TABLE `operation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `compteId` (`compteId`),
  ADD KEY `agenceId` (`agenceId`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `agence`
--
ALTER TABLE `agence`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `compte`
--
ALTER TABLE `compte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `compteepargne`
--
ALTER TABLE `compteepargne`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `comptepayant`
--
ALTER TABLE `comptepayant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `comptesimple`
--
ALTER TABLE `comptesimple`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `operation`
--
ALTER TABLE `operation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `agence_id` FOREIGN KEY (`agence`) REFERENCES `agence` (`id`);

--
-- Contraintes pour la table `compteepargne`
--
ALTER TABLE `compteepargne`
  ADD CONSTRAINT `compte_id_epargne` FOREIGN KEY (`compteId`) REFERENCES `compte` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `comptepayant`
--
ALTER TABLE `comptepayant`
  ADD CONSTRAINT `compte_id_payant` FOREIGN KEY (`compteId`) REFERENCES `compte` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `comptesimple`
--
ALTER TABLE `comptesimple`
  ADD CONSTRAINT `compte_id_simple` FOREIGN KEY (`compteId`) REFERENCES `compte` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `operation`
--
ALTER TABLE `operation`
  ADD CONSTRAINT `agenceId` FOREIGN KEY (`agenceId`) REFERENCES `agence` (`id`),
  ADD CONSTRAINT `compteId` FOREIGN KEY (`compteId`) REFERENCES `compte` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
