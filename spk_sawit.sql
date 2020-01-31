-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 31 Jan 2020 pada 02.56
-- Versi server: 10.4.8-MariaDB
-- Versi PHP: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spk_sawit`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `grup`
--

CREATE TABLE `grup` (
  `id` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `grup`
--

INSERT INTO `grup` (`id`, `nama`) VALUES
(1, 'Dumpy'),
(2, 'SP540'),
(3, 'Yangambi'),
(4, 'Langkat');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kriteria`
--

CREATE TABLE `kriteria` (
  `id` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `tipe` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `kriteria`
--

INSERT INTO `kriteria` (`id`, `nama`, `tipe`) VALUES
(1, 'Rerata Jumlah Tandan', 'benefit'),
(2, 'Rerata Berat Tandan', 'benefit'),
(3, 'Potensi TBS', 'benefit'),
(4, 'Rendemen', 'cost'),
(5, 'Potensi CPO', 'cost'),
(6, 'Tinggi', 'cost'),
(7, 'Panjang Pelepah', 'benefit'),
(8, 'Kerapatan Tanam', 'benefit');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kriteria_varietas`
--

CREATE TABLE `kriteria_varietas` (
  `id` int(11) NOT NULL,
  `varietas_id` int(11) NOT NULL,
  `kriteria_id` int(11) NOT NULL,
  `nilai` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `kriteria_varietas`
--

INSERT INTO `kriteria_varietas` (`id`, `varietas_id`, `kriteria_id`, `nilai`) VALUES
(20, 1, 1, 8),
(21, 1, 2, 25),
(22, 1, 3, 32),
(23, 1, 4, 26),
(24, 1, 5, 7.5),
(25, 1, 6, 67.5),
(26, 1, 7, 6.2),
(27, 1, 8, 130),
(28, 2, 1, 12),
(29, 2, 2, 16),
(30, 2, 3, 30),
(31, 2, 4, 26),
(32, 2, 5, 7.8),
(33, 2, 6, 75),
(34, 2, 7, 6.08),
(35, 2, 8, 130),
(36, 3, 1, 13),
(37, 3, 2, 19.2),
(38, 3, 3, 33),
(39, 3, 4, 26.5),
(40, 3, 5, 8.7),
(41, 3, 6, 77.5),
(42, 3, 7, 5.47),
(43, 3, 8, 143),
(44, 4, 1, 14.1),
(45, 4, 2, 15.4),
(46, 4, 3, 33),
(47, 4, 4, 29.9),
(48, 4, 5, 9.6),
(49, 4, 6, 72),
(50, 4, 7, 5.47),
(51, 4, 8, 143),
(52, 5, 1, 13),
(53, 5, 2, 16),
(54, 5, 3, 39),
(55, 5, 4, 26),
(56, 5, 5, 7.5),
(57, 5, 6, 65),
(58, 5, 7, 6.09),
(59, 5, 8, 130),
(60, 6, 1, 9.3),
(61, 6, 2, 22.8),
(62, 6, 3, 28),
(63, 6, 4, 25.17),
(64, 6, 5, 8.11),
(65, 6, 6, 75),
(66, 6, 7, 5.47),
(67, 6, 8, 143),
(68, 7, 1, 15.3),
(69, 7, 2, 17.2),
(70, 7, 3, 38),
(71, 7, 4, 25.8),
(72, 7, 5, 8.4),
(73, 7, 6, 62.5),
(74, 7, 7, 6.5),
(75, 7, 8, 130),
(76, 8, 1, 12.5),
(77, 8, 2, 19),
(78, 8, 3, 31),
(79, 8, 4, 26.3),
(80, 8, 5, 8.3),
(81, 8, 6, 65),
(82, 8, 7, 5.31),
(83, 8, 8, 143);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengguna`
--

CREATE TABLE `pengguna` (
  `id` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` int(11) NOT NULL,
  `asal_daerah` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `status_login` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pengguna`
--

INSERT INTO `pengguna` (`id`, `nama`, `username`, `password`, `asal_daerah`, `role`, `status_login`) VALUES
(1, 'administrator', 'admin', 123123, 'samarinda', 'admin', 0),
(2, 'pengguna', 'pengguna', 123321, 'samarinda', 'pengguna', 0),
(8, 'baru', 'baru', 123123, 'samarinda', 'pengguna', 0),
(10, 'anas misbah', 'anas', 123123, 'balikpapan', 'pengguna', 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `perbandingankriteria`
--

CREATE TABLE `perbandingankriteria` (
  `id` int(11) NOT NULL,
  `kriteria1_id` int(11) NOT NULL,
  `kriteria2_id` int(11) NOT NULL,
  `nilai` double NOT NULL,
  `pengguna_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `perbandingankriteria`
--

INSERT INTO `perbandingankriteria` (`id`, `kriteria1_id`, `kriteria2_id`, `nilai`, `pengguna_id`) VALUES
(197, 1, 2, 5, 1),
(198, 1, 3, 1, 1),
(199, 1, 4, 4, 1),
(200, 1, 5, 2, 1),
(201, 1, 6, 2, 1),
(202, 1, 7, 2, 1),
(203, 1, 8, 2, 1),
(204, 2, 3, 3, 1),
(205, 2, 4, 2, 1),
(206, 2, 5, 5, 1),
(207, 2, 6, 8, 1),
(208, 2, 7, 6, 1),
(209, 2, 8, 4, 1),
(210, 3, 4, 7, 1),
(211, 3, 5, 2, 1),
(212, 3, 6, 2, 1),
(213, 3, 7, 2, 1),
(214, 3, 8, 5, 1),
(215, 4, 5, 2, 1),
(216, 4, 6, 2, 1),
(217, 4, 7, 4, 1),
(218, 4, 8, 2, 1),
(219, 5, 6, 1, 1),
(220, 5, 7, 2, 1),
(221, 5, 8, 7, 1),
(222, 6, 7, 2, 1),
(223, 6, 8, 9, 1),
(224, 7, 8, 2, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `preferensialternatif`
--

CREATE TABLE `preferensialternatif` (
  `id` int(11) NOT NULL,
  `pengguna_id` int(11) NOT NULL,
  `varietas_id` int(11) NOT NULL,
  `preferensi` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `preferensialternatif`
--

INSERT INTO `preferensialternatif` (`id`, `pengguna_id`, `varietas_id`, `preferensi`) VALUES
(33, 1, 1, 0.16379753866608626),
(34, 1, 2, 0.13751030070472942),
(35, 1, 3, 0.15831303867952287),
(36, 1, 4, 0.1423196854162335),
(37, 1, 5, 0.16613355890473172),
(38, 1, 6, 0.14825179401270625),
(39, 1, 7, 0.1833510018158139),
(40, 1, 8, 0.15778511102033072);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pvector_kriteria`
--

CREATE TABLE `pvector_kriteria` (
  `id` int(11) NOT NULL,
  `kriteria_id` int(11) NOT NULL,
  `pengguna_id` int(11) NOT NULL,
  `nilai` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pvector_kriteria`
--

INSERT INTO `pvector_kriteria` (`id`, `kriteria_id`, `pengguna_id`, `nilai`) VALUES
(1, 1, 1, 0.21461918096804564),
(2, 2, 1, 0.25289303939472696),
(3, 3, 1, 0.17313975400749243),
(4, 4, 1, 0.09264770846436968),
(5, 5, 1, 0.08729878960997937),
(6, 6, 1, 0.09387366584760314),
(7, 7, 1, 0.05047940757161462),
(8, 8, 1, 0.03504845413616821);

-- --------------------------------------------------------

--
-- Struktur dari tabel `varietas`
--

CREATE TABLE `varietas` (
  `id` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `grup_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `varietas`
--

INSERT INTO `varietas` (`id`, `nama`, `grup_id`) VALUES
(1, 'Dy x P SP-1 (Dumpy)', 1),
(2, 'D x P AVROS', 2),
(3, 'D x P Simalungun', 2),
(4, 'D x P PPKS 540', 2),
(5, 'D x P Yangambi', 3),
(6, 'D x P PPKS 718', 3),
(7, 'D x P PPKS 239', 3),
(8, 'D x P Langkat', 4);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `grup`
--
ALTER TABLE `grup`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `kriteria`
--
ALTER TABLE `kriteria`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `kriteria_varietas`
--
ALTER TABLE `kriteria_varietas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `varietas_kriteria_varietas` (`varietas_id`),
  ADD KEY `kriteria_kriteria_varietas` (`kriteria_id`);

--
-- Indeks untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username_unique` (`username`);

--
-- Indeks untuk tabel `perbandingankriteria`
--
ALTER TABLE `perbandingankriteria`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pengguna_perbandingankriteria` (`pengguna_id`),
  ADD KEY `kriteria1_perbandingankriteria` (`kriteria1_id`),
  ADD KEY `kriteria2_perbandingankriteria` (`kriteria2_id`);

--
-- Indeks untuk tabel `preferensialternatif`
--
ALTER TABLE `preferensialternatif`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pengguna_preferensialternatif` (`pengguna_id`),
  ADD KEY `varietas_preferensialternatif` (`varietas_id`);

--
-- Indeks untuk tabel `pvector_kriteria`
--
ALTER TABLE `pvector_kriteria`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pengguna_pvector_kriteria` (`pengguna_id`),
  ADD KEY `kriteria_pvector_kriteria` (`kriteria_id`);

--
-- Indeks untuk tabel `varietas`
--
ALTER TABLE `varietas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `grup_varietas` (`grup_id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `grup`
--
ALTER TABLE `grup`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `kriteria`
--
ALTER TABLE `kriteria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `kriteria_varietas`
--
ALTER TABLE `kriteria_varietas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- AUTO_INCREMENT untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT untuk tabel `perbandingankriteria`
--
ALTER TABLE `perbandingankriteria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=225;

--
-- AUTO_INCREMENT untuk tabel `preferensialternatif`
--
ALTER TABLE `preferensialternatif`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT untuk tabel `pvector_kriteria`
--
ALTER TABLE `pvector_kriteria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `varietas`
--
ALTER TABLE `varietas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `kriteria_varietas`
--
ALTER TABLE `kriteria_varietas`
  ADD CONSTRAINT `kriteria_kriteria_varietas` FOREIGN KEY (`kriteria_id`) REFERENCES `kriteria` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `varietas_kriteria_varietas` FOREIGN KEY (`varietas_id`) REFERENCES `varietas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `perbandingankriteria`
--
ALTER TABLE `perbandingankriteria`
  ADD CONSTRAINT `kriteria1_perbandingankriteria` FOREIGN KEY (`kriteria1_id`) REFERENCES `kriteria` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `kriteria2_perbandingankriteria` FOREIGN KEY (`kriteria2_id`) REFERENCES `kriteria` (`id`),
  ADD CONSTRAINT `pengguna_perbandingankriteria` FOREIGN KEY (`pengguna_id`) REFERENCES `pengguna` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `preferensialternatif`
--
ALTER TABLE `preferensialternatif`
  ADD CONSTRAINT `pengguna_preferensialternatif` FOREIGN KEY (`pengguna_id`) REFERENCES `pengguna` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `varietas_preferensialternatif` FOREIGN KEY (`varietas_id`) REFERENCES `varietas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `pvector_kriteria`
--
ALTER TABLE `pvector_kriteria`
  ADD CONSTRAINT `kriteria_pvector_kriteria` FOREIGN KEY (`kriteria_id`) REFERENCES `kriteria` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pengguna_pvector_kriteria` FOREIGN KEY (`pengguna_id`) REFERENCES `pengguna` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `varietas`
--
ALTER TABLE `varietas`
  ADD CONSTRAINT `grup_varietas` FOREIGN KEY (`grup_id`) REFERENCES `grup` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
