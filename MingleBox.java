package com.minglebox;

import java.util.*;

class Coder
{
	String name;
	String mail;
	double rating;
	public Coder(String name, String mail) {
		this.name = name;
		this.mail = mail;
		this.rating = 0.0;
	}
}

class Buyer
{
	String name;
	String mail;
	double balance;
	public Buyer(String name, String mail) {
		this.name = name;
		this.mail = mail;
		this.balance = 1000.0;
	}
	
}

class Project
{
	String name;
	String description;
	double budget;
	List<Coder> bidders;
	Coder assigncoder;
	public Project(String name, String description, double budget) {
		super();
		this.name = name;
		this.description = description;
		this.budget = budget;
		this.bidders = new ArrayList<>();
		this.assigncoder =null;
	}
	
}
public class MingleBox {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Coder> coders = new ArrayList<>();
		List<Buyer> buyers = new ArrayList<>();
		List<Project> projects = new ArrayList<>();
		
		while(true)
		{
			System.out.println("Registration of Coder: - 1");
			System.out.println("Registration of Buyer: - 2");
			System.out.println("Add project for bidding: - 3");
			System.out.println("Select Coders through bidding: - 4");
			System.out.println("Assign project to coder: - 5");
			System.out.println("Payment for completed work: - 6");
			System.out.println("Exit - 7");
			System.out.print("Enter the choice of the bidder: ");
			int choice = sc.nextInt();
			
			switch(choice)
			{
			case 1:
				System.out.println("Coder's name: ");
				String coderName = sc.next();
				System.out.println("Coder's Email: ");
				String coderMail = sc.next();
				coders.add(new Coder(coderName,coderMail));
				break;
			
			case 2:
				System.out.println("Buyer's name: ");
				String buyerName = sc.next();
				System.out.println("Buyer's Email: ");
				String buyerMail = sc.next();
				buyers.add(new Buyer(buyerName,buyerMail));
				break;
				
			case 3:
				System.out.println("Project name : ");
				String projectName = sc.next();
				System.out.println("Description : ");
				String description = sc.next();
				System.out.println("Budget :");
				double budget = sc.nextDouble();
				projects.add(new Project(projectName,description,budget));
				break;
			case 4:
				System.out.println("available projects: ");
				for (int i=0;i<projects.size();i++)
				{
					System.out.println((i+1) + "." + projects.get(i).name);
				}
				System.out.println("Select project: ");
				int projectid = sc.nextInt()-1;
				if(projectid>=0 && projectid<=projects.size())
				{
					Project selectproject = projects.get(projectid);
					List<Coder> projectbidders = selectproject.bidders;
					
					System.out.println("Available coders: ");
					for(int i=0;i<coders.size();i++)
					{
						System.out.println((i+1) + "." + coders.get(i).name);
					}
					System.out.println("Select coders for bidding (comma-seperated indices):");
					String selectcoderinput = sc.next();
					String[] selectcoderindices = selectcoderinput.split(",");
					for(String indstr: selectcoderindices)
					{
						int coderindex = Integer.parseInt(indstr.trim())-1;
						if(coderindex>=0 && coderindex<=coders.size())
						{
							projectbidders.add(coders.get(coderindex));
						}
					}
					selectproject.bidders = projectbidders;
					System.out.println("Coders added for biddin on the project.");
	 			}
				else
				{
					System.out.println("Invalid project selection.");
				}
				break;
			case 5:
				System.out.println("Available projects with assigned coders: ");
				for(int i=0;i<projects.size();i++)
				{
					Project p = projects.get(i);
					if(p.bidders.size()>0 && p.assigncoder==null)
					{
						System.out.println((i+1) + "." + p.name);
					}
				}
				System.out.println("Select project to assign coder: ");
				int assignprojectindex = sc.nextInt()-1;
				if(assignprojectindex>=0 && assignprojectindex<projects.size())
				{
					Project assignproject = projects.get(assignprojectindex);
					
					if(assignproject.bidders.size()>0)
					{
						System.out.println("Available bidders for the project: ");
						List<Coder> bidders = assignproject.bidders;
						for(int i=0;i<bidders.size();i++)
						{
							System.out.println((i+1) + "." + bidders.get(i).name);
						}
						System.out.println("Select coder to assign: ");
						int selectcoderindex = sc.nextInt()-1;
						if(selectcoderindex>=0 && selectcoderindex<bidders.size())
						{
							Coder selectedcoder = bidders.get(selectcoderindex);
							assignproject.assigncoder = selectedcoder;
							System.out.println(selectedcoder.name + " assigned to " + assignproject.name);
						}
						else
						{
							System.out.println("Invalid coder selection.");
						}
					}
					else
					{
						System.out.println("No available coders for this project.");
					}
				}
				else
				{
					System.out.println("Invalid project selection.");
				}
				break;
				
			case 6:
				System.out.println("Available projects with assigned coders:");
				for(int i=0;i<projects.size();i++)
				{
					Project p = projects.get(i);
					if(p.assigncoder!=null)
					{
						System.out.println((i+1) + "." + p.name + "(Coder: " + p.assigncoder.name + ")");
					}
				}
				System.out.println("Select project for payment: ");
				int selectpaymentprojectid = sc.nextInt()-1;
				if(selectpaymentprojectid>=0 && selectpaymentprojectid<projects.size())
				{
					Project selectpaymentproject = projects.get(selectpaymentprojectid);
					if(selectpaymentproject.assigncoder!=null)
					{
						System.out.println("Project: " + selectpaymentproject.name);
						System.out.println("Coder: " + selectpaymentproject.assigncoder.name);
						System.out.println("Budget: $" + selectpaymentproject.budget);
						System.out.println("Enter payment amount: ");
						double paymentamt=sc.nextDouble();
						if(paymentamt<=selectpaymentproject.budget)
						{
							Buyer buyer = buyers.get(0);
							buyer.balance -= paymentamt;
							selectpaymentproject.assigncoder.rating +=0.1;
							System.out.println("Payment successful. ");
						}
						else
						{
							System.out.println("Payment amount exceeds budget.");
						}
					}
					else
					{
						System.out.println("No coder assigned to this project. ");
					}
				}
				else
				{
					System.out.println("Invalid project selection. ");
				}
				break;
			case 7:
				System.out.println("Exiting the program. ");
				sc.close();
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. ");
				break;
			}
		}
	}
}
