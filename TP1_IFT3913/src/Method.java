import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Method {
		private String signature;
		private String line;
		private List<String>  file;
		private List<String> contentMethod;
		
		public Method(String name, String line, List<String> fileContent)
		{
			this.signature = name;
			this.line = line;
			this.file = fileContent;
			this.contentMethod = new ArrayList<String>();
			
		}
		
		public void findMethodContent()
		{
			int countAcco = 0;
			boolean inMethod = false;
			for (String temp : this.file)
			{
				System.out.println(temp);
				System.out.println(line);
				if(temp.equals(line))
				{
					this.contentMethod.add(temp);
					System.out.println("test4");
					inMethod = true;
					if(temp.contains("{"))
					{
						countAcco++;
					}
					System.out.println(countAcco);
				}
				
				else if (inMethod == true)
				{
					System.out.println(countAcco);
					System.out.println("test3");
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
					System.out.println("ICI MON CHUM" + this.contentMethod);
						return;
					}
				}
			}
		}

		public String getSignature() {
			return this.signature;
		}

		public String getLine() {
			return this.line;
		}

		public List<String> getFile() {
			return this.file;
		}

		public List<String> getContentMethod() {
			return this.contentMethod;
		}
		
}
