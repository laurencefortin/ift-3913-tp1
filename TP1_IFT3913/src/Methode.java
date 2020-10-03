import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Methode {
		private String signature;
		private String line;
		private List<String>  file;
		private List<String> contentMethod;
		private List<String> commentsBeforeMethod;
		
		public Methode(String name, String line, List<String> fileContent)
		{
			this.signature = name;
			this.line = line;
			this.file = fileContent;
			this.contentMethod = new ArrayList<String>();
			findMethodContent();
			this.commentsBeforeMethod = new ArrayList<String>();
			findCommentsBefore();
		}
		
		public void findMethodContent()
		{
			int countAcco = 0;
			boolean inMethod = false;
			for (String temp : this.file)
			{
				if(temp.equals(line))
				{
					this.contentMethod.add(temp);
					inMethod = true;
					if(temp.contains("{"))
					{
						countAcco++;
					}
				}
				
				else if (inMethod == true)
				{
					if(temp != null && !temp.trim().isEmpty())
					{		
						this.contentMethod.add(temp);
						if(!temp.contains("}") && temp.contains("{"))
						{
							countAcco++;
						}
						if(temp.contains("}") && !temp.contains("{"))
						{
							countAcco--;
						}
						if(countAcco == 0)
						{
							inMethod = false;
							return;
						}
					}
				}
			}
		}
		
		public int methode_LOC()
		{
			return contentMethod.size();
		}
		
		public int methode_CLOC()
		{
			int count = 0;
			boolean inComment = false;
			for (String temp : contentMethod) 
			{
				if(!inComment)
				{
						
					if(temp.contains("//")) 
					{
						count++;
					}	
					if(temp.contains("/*") || temp.contains("/**"))
					{
						count++;
						inComment = true;
					}
					if(temp.contains("*/"))
					{
						inComment = false;
					}
				}
				else
				{
					if(temp != null && !temp.trim().isEmpty())
					{
						count++;
					}
					if(temp.contains("*/"))
					{
						inComment = false;
					}
				}
	        }
			return count + commentsBeforeMethod.size();
		}
		
		
		public void findCommentsBefore()
		{
			boolean inComment = false;
			boolean beforeMethod = false;
			boolean onlyEmpty = true;
			ListIterator<String> listIterator = file.listIterator(file.size());
			String current = listIterator.previous();
			while (listIterator.hasPrevious()) 
			{
				if(current.equals(line))
				{
					current = listIterator.previous();
					beforeMethod = true;
				}
				

				if(beforeMethod)
				{
					if(!inComment)
					{
						if(current == null || current.trim().isEmpty())
						{
							current = listIterator.previous();
						}
						
						else if(current.contains("//"))
						{
							if(onlyEmpty = true)
							{
								onlyEmpty = false;
							}
							commentsBeforeMethod.add(current);
							current = listIterator.previous();
						}
						
						else if(current.contains("*/"))
						{
							if(onlyEmpty = true)
							{
								onlyEmpty = false;
							}
							inComment = true;
							commentsBeforeMethod.add(current);
							current = listIterator.previous();
						}
						else
						{
							if(onlyEmpty)
							{
								commentsBeforeMethod.clear();
							}
							return;
						}
					}
					else
					{
						if(current.contains("/**") || current.contains("/*"))
						{
							commentsBeforeMethod.add(current);
							return;
						}
						commentsBeforeMethod.add(current);
						current = listIterator.previous();
					}
		
				}
				else {
					current = listIterator.previous();
				}
				
			}
		}

		
		public int CC()
		{
			String regex = "(if|else|else if|do-while|while)\\s*\\(((?:[^\\(\\)]|\\(\\))*)\\)\\s*";
			int count = 1;
			int returnBeforeEnd = 0;//variable sert a quoi??
			for (String temp : contentMethod) 
			{
				if(temp.contains("return"))
				{
					returnBeforeEnd++;
				}
				if(temp.matches(regex))
				{
					count++;
					if(temp.contains("&&"))
					{
						count++;
					}
					if(temp.contains("||"))
					{
						count++;
					}
				}
			}
			return count;
		}
		
		public String getSignature() {
			return signature;
		}

		public String getLine() {
			return line;
		}

		public List<String> getFile() {
			return file;
		}

		public List<String> getContentMethod() {
			return contentMethod;
		}

		public List<String> getCommentsBeforeMethod() {
			return commentsBeforeMethod;
		}

		
}