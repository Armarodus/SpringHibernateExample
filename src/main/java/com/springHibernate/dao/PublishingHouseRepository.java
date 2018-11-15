package com.springHibernate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springHibernate.models.PublishingHouse;

@Repository
public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Integer> {

}
