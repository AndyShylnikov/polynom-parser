create database polymonials_db if not exists;

CREATE TABLE `expressions` (
                               `id` int(11) NOT NULL,
                               `original` varchar(255) NOT NULL,
                               `polynomial_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `polynomials` (
                               `id` int(11) NOT NULL,
                               `simplified` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `terms` (
                         `id` int(11) NOT NULL,
                         `coefficient` int(11) NOT NULL DEFAULT 0,
                         `exponent` int(11) NOT NULL DEFAULT 0,
                         `polynomial_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


ALTER TABLE `expressions`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uk_original_expression` (`original`) USING BTREE,
  ADD KEY `fk_polynomial_id` (`polynomial_id`);

ALTER TABLE `polynomials`
    ADD PRIMARY KEY (`id`);

ALTER TABLE `terms`
    ADD PRIMARY KEY (`id`),
  ADD KEY `polynomial_id` (`polynomial_id`);

ALTER TABLE `expressions`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `polynomials`
--
ALTER TABLE `polynomials`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `terms`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `expressions`
    ADD CONSTRAINT `fk_expression_polynomial_id` FOREIGN KEY (`polynomial_id`) REFERENCES `polynomials` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `terms`
    ADD CONSTRAINT `fk_term_polynomial_id` FOREIGN KEY (`polynomial_id`) REFERENCES `polynomials` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

