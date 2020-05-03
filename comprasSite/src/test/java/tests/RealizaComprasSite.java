package tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.Evidencias;

public class RealizaComprasSite {
	
	private WebDriver driver;
	private Evidencias evidencias =  new Evidencias();

	@Before
	public void setUp() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Workspace\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
		
		//STEP 1 - Carrega o site de compras
		driver.get("https://www.americanas.com.br/");
		assertTrue("Título da página difere do esperado", driver.getTitle().contentEquals("Americanas - Tudo. A toda hora. Em qualquer lugar."));
		evidencias.takeScreenShot(driver, "siteCarregado");
		
		//STEP 2 - Realiza a busca no site
		driver.findElement(By.xpath("//*[@id=\"h_search-input\"]")).sendKeys("Iphone");
		evidencias.takeScreenShot(driver, "digitaBusca");
		
		//STEP 3 - Clica no botão buscar
		driver.findElement(By.xpath("//*[@id=\"h_search-btn\"]")).click();
		evidencias.takeScreenShot(driver, "clicaBotaoBusca");		
		Thread.sleep(3000);
		
		//STEP 4 - Verifica o retorno dos itens
		boolean retornouProdutos = driver.getPageSource().contains("produtos");		
		driver.findElement(By.xpath("//*[@id=\"content-middle\"]/div[6]/div/div/div/div[1]/div[1]/div/div[2]/a/section/div[2]/div[1]/h2")).click();		
		assertTrue(retornouProdutos);		
		evidencias.takeScreenShot(driver, "listaProdutos");
		
		//STEP 5 - Preenche o campo CEP para verificar o valor do frete
		driver.findElement(By.xpath("//*[@id=\"freight-field\"]")).sendKeys("04776100");		
		Thread.sleep(2000);		
		evidencias.takeScreenShot(driver, "preencheCEP");
		
		//STEP 6 - Clica no botão OK para verificar o valor do frete
		driver.findElement(By.xpath("//*[@id=\"freight-field-button\"]/div")).click();		
		Thread.sleep(2000);		
		JavascriptExecutor jse = (JavascriptExecutor)driver;		
		jse.executeScript("window.scrollBy(0,500)");		
		Thread.sleep(5000);
		
		//STEP 7 - Clica no botão comprar p/ adicionar ao carrinho de compras
		driver.findElement(By.xpath("//*[@id=\"btn-buy\"]/div/span")).click();
		evidencias.takeScreenShot(driver, "clicaBotaoComprar");
		
		
		//STEP 8 - Opções de seguro do aparelho
		driver.findElement(By.xpath("//*[@id=\"btn-continue\"]")).click();				
		Thread.sleep(5000);
		evidencias.takeScreenShot(driver, "clicaBotaoSeguros");
		
		//STEP 9 - Clica no botão comprar
		driver.findElement(By.xpath("//*[@id=\"buy-button\"]")).click();		
		Thread.sleep(3000);
		evidencias.takeScreenShot(driver, "clicaBotaoContinuar");
		
		//STEP 10 - Clica na opção minha cesta
		driver.findElement(By.xpath("//*[@id=\"h_steps\"]/li[1]/div/a")).click();
		evidencias.takeScreenShot(driver, "clicaCesta");		
		Thread.sleep(3000);		
		
		//STEP 11 - Valida o conteúdo do carrinho
		if(driver.getPageSource().contains("Resumo do pedido")){
			System.out.println("O item foi adicionado com sucesso ao carrinho"); 
			}
		else{
			System.out.println("O item não foi adicionado ao carrinho"); 
			}
		
		evidencias.takeScreenShot(driver, "resumoPedido");
		
		
	}

}
