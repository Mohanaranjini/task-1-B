package com.example.task.B;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

@SpringBootApplication
public class Application implements CommandLineRunner{
	@Autowired
	private employeerepository Employeerepository;

	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}

	@Override
	@Transactional
	public void run(String...args) throws Exception {
		boolean dataImportedSuccessfully = false;

		try {// Load the XML file from the classpath
			Resource resource = new ClassPathResource("employee.xml");
			InputStream inputStream = resource.getInputStream();


			// Parse the XML document
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputStream);
			doc.getDocumentElement().normalize();

			// Extract data from XML and insert into the database
			NodeList nodeList = doc.getElementsByTagName("employee");
			for (int temp = 0; temp < nodeList.getLength(); temp++) {
				Element element = (Element) nodeList.item(temp);
				String name = element.getElementsByTagName("name").item(0).getTextContent();
				String position = element.getElementsByTagName("position").item(0).getTextContent();
				String department = element.getElementsByTagName("department").item(0).getTextContent();


				// Check if the book already exists in the database

				if (position.equals("Quality Engineer")) {
					continue;
				} else {
					Employee Employee = new Employee();
					Employee.setDepartment(department);
					Employee.setPosition(position);
					Employee.setName(name);

					Employeerepository.save(Employee);
					dataImportedSuccessfully = true;
				}
			}
			if (dataImportedSuccessfully) {
				System.out.println("");
				System.out.println(" ");

				System.out.println("Data imported successfully.");
			} else {
				System.out.println("No new data imported. All books already exist in the database.");

				System.out.println("");
				System.out.println(" ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}