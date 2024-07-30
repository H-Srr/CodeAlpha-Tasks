package Tasks;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Stock{
	private String symbol;
	private int quantity;
	private double averagePrice;
	
	public Stock(String symbol, int quantity, double averagePrice) {
		this.symbol = symbol;
		this.quantity = quantity;
		this.averagePrice = averagePrice;
	}
	public String getSymbol() {
		return symbol;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getAveragePrice() {
		return averagePrice;
	}
	public void addQuantity(int quantity, double price) {
		double totalCost = this.quantity * this.averagePrice + quantity * price;
		this.quantity += quantity;
		this.averagePrice = totalCost/this.quantity;
	}
	public void removeQuantity(int quantity) {
		this.quantity-=quantity;
	}
}

class MarketData{
	private HashMap<String, Double> stockPrices;
	
	public MarketData() {
		stockPrices = new HashMap<>();
		stockPrices.put("Apple", 231.25);
		stockPrices.put("Google", 191.82);
		stockPrices.put("Tesla", 286.02);
		stockPrices.put("Amazon", 185.45);
		stockPrices.put("Microsoft", 352.89);
	}
	public double getPrice(String symbol) {
		return stockPrices.getOrDefault(symbol, 0.0);
	}
	public void updatePrice(String symbol, double newPrice) {
		stockPrices.put(symbol, newPrice);
	}
	public void printMarketData() {
		System.out.println();
		System.out.println("Current Market Prices: ");
		for(String symbol : stockPrices.keySet()) {
			System.out.println(symbol + ": $" + stockPrices.get(symbol));
		}
		System.out.println();
	}
}


class Portfolio{
	private ArrayList<Stock> stocks;
	private double Balance;
	
	public Portfolio(double cash) {
		stocks = new ArrayList<>();
		Balance = cash;
	}
	public double getBalance() {
		return Balance;
	}
	public void buyStock(String symbol, int quantity, double price) {
		for(Stock stock : stocks) {
			if(stock.getSymbol().equals(symbol)) {
			Balance -= quantity * price;
			stock.addQuantity(quantity, price);
			return;
			}
		}
		stocks.add(new Stock(symbol, quantity, price));
		Balance -= quantity * price;
	}
	public void sellStock(String symbol, int quantity, double price) {
		Stock toRemove = null;
		for(Stock stock : stocks) {
			if (stock.getSymbol().equals(symbol)) {
				if (stock.getQuantity()< quantity) {
					System.out.println("Not enough stock to sell");
					return;
				}
				else {
					stock.removeQuantity(quantity);
					Balance+= quantity*price;
					if (stock.getQuantity() == 0) {
						toRemove = stock;
					}
					break;
				}
			}
		}
		if(toRemove!=null) {
			stocks.remove(toRemove);
		}
	}
	public void printPortfolio() {
		System.out.println("Portfolio: ");
		for(Stock stock : stocks) {
			System.out.println(stock.getSymbol()+": "+stock.getQuantity() + " Shares - Single Share Price: $"+stock.getAveragePrice());
		}
		System.out.println("Cash Balance: $" + Balance);
		System.out.println();
	}
	public void printBalance() {
		System.out.println("Total Portfolio value: $" + Balance);;
		System.out.println();
	}
	public double getTotalValue(MarketData marketData) {
		double totalValue = Balance;
		for(Stock stock : stocks) {
			totalValue += stock.getQuantity()*marketData.getPrice(stock.getSymbol());
		}
		return totalValue;
	}
}

public class StockTradingPlatform {
	public static void main(String[] args) {
		System.out.println("\t Welcome to the Stock Trading Platform!");
		Scanner input = new Scanner(System.in);
		MarketData marketData = new MarketData();
		System.out.println("Enter your initial cash balance: ");
		int initialBalance = input.nextInt();
		Portfolio portfolio = new Portfolio(initialBalance);
		boolean flag = true;
		
		
		while(flag == true) {
			System.out.println();
			System.out.println("1. View Market Data");
			System.out.println("2. Buy Stock");
			System.out.println("3. Sell Stock");
			System.out.println("4. View Portfolio");
			System.out.println("5. View Potfolio Value");
			System.out.println("6. Exit");
			System.out.println();
			System.out.println("Choose an option: ");
			int choice = input.nextInt();
			
			switch(choice) {
			case 1:
				marketData.printMarketData();
				break;
			case 2:
				System.out.println("Enter stock symbol to buy: ");
				String SymbolBuy = input.next();
				System.out.println("Enter desired quantity: ");
				int QuantityBuy = input.nextInt();
				double PriceBuy = marketData.getPrice(SymbolBuy);
				if(PriceBuy > 0 && portfolio.getBalance() >= QuantityBuy * PriceBuy) {
					portfolio.buyStock(SymbolBuy, QuantityBuy, PriceBuy);
					System.out.println("Bought " + QuantityBuy+ " share(s) of " + SymbolBuy + " for " + PriceBuy + " each");
				} else {
					System.out.println("Not enough cash to buy or invalid input!");
				}
				break;
			case 3: 
				System.out.println("Enter stock symbol to sell: ");
				String SymbolSell = input.next();
				System.out.println("Enter desired quantity to sell: ");
				int QuantitySell = input.nextInt();
				double PriceSell = marketData.getPrice(SymbolSell);
				if (PriceSell > 0) {
					portfolio.sellStock(SymbolSell, QuantitySell, PriceSell);
					System.out.println("Sold " + QuantitySell + " share(s) of " + SymbolSell + " for " + PriceSell + " each");
				} else {
					System.out.println("Not enough stock or invalid input!");
				}
				break;
			case 4:
				portfolio.printPortfolio();
				break;
			case 5:
				portfolio.printBalance();
				break;
			case 6:
				System.out.println("Thank you for checking the trading platform out!");
				System.out.println("Goodbye!");
				flag = false;
				break;
			}
		}
	}
}
