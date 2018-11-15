package com.springHibernate.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springHibernate.dao.PublishingHouseRepository;
import com.springHibernate.interfaces.SaveInterface;
import com.springHibernate.models.PublishingHouse;

@Service
public class PublishingHouseService implements SaveInterface{

	private static final String XLSX_TEMPLATE= "Ph_template.xlsx";
	private static final String XLSX_OUTPUT="ph_output.xlsx";
	private static final String CONTEXT_XLSX_VAR = "phs";
	
	
	@Autowired
	private PublishingHouseRepository houseRepository;


	public void save(PublishingHouse house) {
		houseRepository.save(house);
	}

	public void remove(Integer id) {
		houseRepository.deleteById(id);
	}

	public List<PublishingHouse> getAll() {

		return houseRepository.findAll();
	}

	public PublishingHouse getById(Integer id) {
		return houseRepository.findById(id).get();
	}

	public void saveToExcelBookReport() {
		List<PublishingHouse> phs = this.getAll();
		try {
			InputStream is = new FileInputStream(new File(XLSX_TEMPLATE));

			OutputStream os = new FileOutputStream(XLSX_OUTPUT);
			Context context = new Context();
			context.putVar(CONTEXT_XLSX_VAR, phs);
			JxlsHelper.getInstance().processTemplate(is, os, context);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
