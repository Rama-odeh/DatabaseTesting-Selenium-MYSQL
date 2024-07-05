package dataBseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mysql.cj.xdevapi.Statement;

public class myTestCases {

	Connection con;

	java.sql.Statement stmt;

	ResultSet rs;

	String url = "https://magento.softwaretestingboard.com/customer/account/create/";
	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void Setup() throws SQLException {

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "RAMA");

	}

	@Test(priority = 1)
	public void addData() throws SQLException {

		String Query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country)"
				+ "VALUES (79, 'ABC', 'Rama', 'Odeh', '643-8291', 'Amman', 'Amman', 'Jordan')";
		stmt = con.createStatement();
		int insertRow = stmt.executeUpdate(Query);
		Assert.assertEquals(insertRow > 0, true);
	}

	@Test(priority = 2)
	public void updateData() throws SQLException {

		String Query = "update customers set customerName ='Rama Abdullah' where customerNumber = 79";
		stmt = con.createStatement();
		int updateRow = stmt.executeUpdate(Query);
		Assert.assertEquals(updateRow > 0, true);

	}

	@Test(priority = 3)
	public void getData() throws SQLException {

		stmt = con.createStatement();
		rs = stmt.executeQuery("select * from customers where customerNumber=79");

		int customerNumber;
		String customerName = null;

		while (rs.next()) {
			customerNumber = rs.getInt("customerNumber");
			customerName = rs.getNString("customerName");

		}

		driver.get(url);
		driver.findElement(By.id("firstname")).sendKeys(customerName);

	}

	@Test(priority = 4)
	public void deleteData() throws SQLException {
		String Query = "delete from customers where customerNumber = 79";
		stmt = con.createStatement();
		int deleteRow = stmt.executeUpdate(Query);
		Assert.assertEquals(deleteRow > 0, true);
	}

}
