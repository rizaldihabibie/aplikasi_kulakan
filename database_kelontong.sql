-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 14, 2017 at 12:45 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `database_kelontong`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_barang` int(11) NOT NULL,
  `kode_barang` varchar(200) NOT NULL,
  `nama_barang` varchar(200) NOT NULL,
  `deskripsi_barang` text NOT NULL,
  `satuan_terbesar` varchar(50) NOT NULL,
  `satuan_terkecil` varchar(50) NOT NULL,
  `jml_satuan_terkecil` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_barang`, `kode_barang`, `nama_barang`, `deskripsi_barang`, `satuan_terbesar`, `satuan_terkecil`, `jml_satuan_terkecil`) VALUES
(1, 'brg001', 'barang 1', 'sdfdsfsdfsd', 'besar', 'kecil', 10);

-- --------------------------------------------------------

--
-- Table structure for table `daftar_harga`
--

CREATE TABLE `daftar_harga` (
  `id_harga` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `harga_a` bigint(20) NOT NULL,
  `harga_b` bigint(20) NOT NULL,
  `harga_c` bigint(20) NOT NULL,
  `harga_d` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `kulakan`
--

CREATE TABLE `kulakan` (
  `kode_pembelian` varchar(100) NOT NULL,
  `nota_pembelian` varchar(200) NOT NULL,
  `kode_supplier` varchar(100) NOT NULL,
  `id_barang` varchar(100) NOT NULL,
  `satuan` varchar(50) NOT NULL,
  `jumlah` double NOT NULL,
  `tanggal_kulakan` date NOT NULL,
  `harga_netto` bigint(20) NOT NULL,
  `diskon` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kulakan`
--

INSERT INTO `kulakan` (`kode_pembelian`, `nota_pembelian`, `kode_supplier`, `id_barang`, `satuan`, `jumlah`, `tanggal_kulakan`, `harga_netto`, `diskon`) VALUES
('PMBL0001', '', 'SPL001', '1', 'kecil', 10, '2017-02-20', 10000, 0),
('PMBL0002', '001', 'SPL001', '1', 'kecil', 10, '2017-02-20', 10000, 2),
('PMBL0003', '', 'SPL003', '1', 'kecil', 10, '2017-01-11', 100000, 0),
('PMBL0004', '0002', 'SPL003', '1', 'besar', 10, '2017-01-20', 100000, 0),
('PMBL0005', '', 'SPL001', '1', 'kecil', 10, '2017-03-04', 124356, 10),
('PMBL0006', '002', 'SPL002', '1', 'kecil', 20, '2017-03-06', 4354353, 10);

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `id_penjualan` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `no_nota` varchar(200) NOT NULL,
  `kuantitas` double NOT NULL,
  `diskon` int(11) NOT NULL,
  `satuan` varchar(50) NOT NULL,
  `harga_satuan` int(11) NOT NULL,
  `total_harga` int(11) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`id_penjualan`, `id_barang`, `no_nota`, `kuantitas`, `diskon`, `satuan`, `harga_satuan`, `total_harga`, `tanggal`) VALUES
(1, 1, '001', 3, 0, 'kecil', 1000, 3000, '2017-02-20'),
(2, 1, '', 4, 0, 'kecil', 1000, 4000, '2017-02-20'),
(3, 1, '', 1, 0, 'besar', 11000, 11000, '2017-02-20'),
(4, 1, '', 1, 0, 'besar', 121211, 121211, '2017-02-27'),
(5, 1, '', 3, 0, 'kecil', 34343, 103029, '2017-01-12');

-- --------------------------------------------------------

--
-- Table structure for table `retur_pembelian`
--

CREATE TABLE `retur_pembelian` (
  `id` int(11) NOT NULL,
  `nota_pembelian` varchar(200) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `tanggal_retur` date NOT NULL,
  `jumlah` double NOT NULL,
  `satuan` varchar(50) NOT NULL,
  `keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `retur_pembelian`
--

INSERT INTO `retur_pembelian` (`id`, `nota_pembelian`, `id_barang`, `tanggal_retur`, `jumlah`, `satuan`, `keterangan`) VALUES
(1, '001', 1, '2017-03-05', 1, 'kecil', 'Barang KW cuuuy'),
(2, '0002', 1, '2017-03-05', 1, 'besar', 'ghcghcghghchg');

-- --------------------------------------------------------

--
-- Table structure for table `retur_penjualan`
--

CREATE TABLE `retur_penjualan` (
  `id` int(11) NOT NULL,
  `nota_penjualan` varchar(200) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `tanggal_retur` date NOT NULL,
  `keterangan` varchar(300) NOT NULL,
  `jumlah` double NOT NULL,
  `satuan` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `retur_penjualan`
--

INSERT INTO `retur_penjualan` (`id`, `nota_penjualan`, `id_barang`, `tanggal_retur`, `keterangan`, `jumlah`, `satuan`) VALUES
(1, '001', 1, '2017-03-02', 'Barangnya jelek cuuy', 1, 'kecil'),
(2, '2334', 1, '2017-03-02', 'Barangnya jelek cuuy', 1, 'kecil'),
(3, '001', 1, '2017-03-02', 'barang tidak sesuai', 2, 'kecil'),
(4, '001', 1, '2017-03-05', 'fdfgdgfdf', 1, 'besar');

-- --------------------------------------------------------

--
-- Table structure for table `stock_barang`
--

CREATE TABLE `stock_barang` (
  `id_stock` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `jumlah` double NOT NULL,
  `tanggal` date NOT NULL,
  `status` enum('STOCK_BARU','STOCK_LAMA') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stock_barang`
--

INSERT INTO `stock_barang` (`id_stock`, `id_barang`, `jumlah`, `tanggal`, `status`) VALUES
(1, 1, 10, '2017-02-20', 'STOCK_LAMA'),
(2, 1, 20, '2017-02-20', 'STOCK_LAMA'),
(3, 1, 17, '2017-02-20', 'STOCK_LAMA'),
(4, 1, 13, '2017-02-20', 'STOCK_LAMA'),
(5, 1, 3, '2017-02-20', 'STOCK_LAMA'),
(6, 1, 13, '2017-01-11', 'STOCK_LAMA'),
(7, 1, 113, '2017-01-20', 'STOCK_LAMA'),
(8, 1, 103, '2017-02-27', 'STOCK_LAMA'),
(9, 1, 100, '2017-01-12', 'STOCK_LAMA'),
(10, 1, 110, '2017-03-04', 'STOCK_LAMA'),
(11, 1, 130, '2017-03-06', 'STOCK_BARU');

-- --------------------------------------------------------

--
-- Table structure for table `table_supplier`
--

CREATE TABLE `table_supplier` (
  `kode_supplier` varchar(100) NOT NULL,
  `nama_supplier` varchar(100) NOT NULL,
  `alamat_supplier` varchar(100) NOT NULL,
  `telpon_supplier` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `table_supplier`
--

INSERT INTO `table_supplier` (`kode_supplier`, `nama_supplier`, `alamat_supplier`, `telpon_supplier`) VALUES
('SPL001', 'Supplier 1', 'alamat 1', 'telpon 1'),
('SPL002', 'Supplier 2', 'alamat 2', 'telpon 2'),
('SPL003', 'Supllier 3', 'Alamat 3', 'telpon 3'),
('SPL004', 'Supplier 5', 'Alamat 5', 'Telpon 5');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indexes for table `kulakan`
--
ALTER TABLE `kulakan`
  ADD PRIMARY KEY (`kode_pembelian`),
  ADD KEY `kode_supplier` (`kode_supplier`),
  ADD KEY `kode_barang` (`id_barang`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`id_penjualan`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indexes for table `retur_pembelian`
--
ALTER TABLE `retur_pembelian`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `retur_penjualan`
--
ALTER TABLE `retur_penjualan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stock_barang`
--
ALTER TABLE `stock_barang`
  ADD PRIMARY KEY (`id_stock`);

--
-- Indexes for table `table_supplier`
--
ALTER TABLE `table_supplier`
  ADD PRIMARY KEY (`kode_supplier`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `id_barang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `penjualan`
--
ALTER TABLE `penjualan`
  MODIFY `id_penjualan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `retur_pembelian`
--
ALTER TABLE `retur_pembelian`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `retur_penjualan`
--
ALTER TABLE `retur_penjualan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `stock_barang`
--
ALTER TABLE `stock_barang`
  MODIFY `id_stock` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
