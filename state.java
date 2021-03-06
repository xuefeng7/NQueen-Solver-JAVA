import java.util.ArrayList;


public class state {
	
	
	public ArrayList<Queen> config;
	public int Score;
	public int limit;
	
	public state(int n){
		
		config=new ArrayList<Queen>();
		this.limit=n;
		//getScore();
	}
	public void setLimit(int n){
		this.limit=n;
	}
	public void setConfig(ArrayList<Queen> c){
		for(Queen q:c){
			this.config.add(q);
		}
	}

	public void getScore(){
		
		int score=0;
		
		//copy original config
		ArrayList<Queen> copy=new ArrayList<Queen> ();
		ArrayList<Queen> ref=new ArrayList<Queen> ();
		
		for(Queen q:this.config){
			copy.add(q);
			ref.add(q);
		}
		
		int count=0;
		while(count<this.limit){
			
			for(Queen qq:this.config){
				score+=getOneQueenScore(qq);
				ref.remove(qq);
				break;
			}
			this.config.clear();
			for(Queen q:ref){
				this.config.add(q);
			}
			count++;
		}
		
		for(Queen q:copy){
			
			this.config.add(q);
		}
		
		this.Score=score;
		public int getOneQueenScore(Queen q){
		
		int score=0;
		
		score+=getSameRowConflict(q);
		score+=getSameColumnConflict(q);
		score+=getDiagonalConflict(q);
		
		return score;
	}
	public int getSameRowConflict(Queen q){
		int score=0;
		int qx=q.location.x;
		
		for(Queen qq:this.config){
			if(!q.equal(qq)){
				if(qq.location.x==qx){
					//System.out.println(q+" conflicts with "+qq+"in row");
					score++;
				}
			}
		}
		return score;
	}
	public void removeByIndex(int index){
		
		state ref=new state(this.limit);
		ref.setConfig(this.config);
		
		for(Queen q:ref.config){
			
			if(q.index==index){
				
				this.config.remove(q);
			}
		}
	}
	public int getOneQueenScore(Queen q){
		
		int score=0;
		
		score+=getSameRowConflict(q);
		score+=getSameColumnConflict(q);
		score+=getDiagonalConflict(q);
		
		return score;
	}
	public int getSameColumnConflict(Queen q){
		int score=0;
		int qy=q.location.y;
		
		for(Queen qq:this.config){
			if(!q.equal(qq)){
				if(qq.location.y==qy){
					//System.out.println(q+" conflicts with "+qq+"in column");
					score++;
				}
			}
		}
		return score;
	}
	

	public int  getDiagonalConflict(Queen q){
		
		int score=0;
		
		int qx=q.location.x;
		int qy=q.location.y;
		
		//case 1: upleft diagonal
		while(qx>=1&&qy>=1){
			for(Queen qq:this.config){
				if(!q.equal(qq)){
					if(qq.location.x==qx&&qq.location.y==qy){
						//System.out.println(q+" conflicts with "+qq+"in upleft");
						score++;
					}
				}
			}
			qx=qx-1;
			qy=qy-1;
		}
		qx=q.location.x;
		qy=q.location.y;
		//case 2: downright diagonal
				while(qx<=this.limit&&qy<=this.limit){
					
					//System.out.println("("+qx+","+qy+")");
					for(Queen qq:this.config){
						if(!q.equal(qq)){
							if(qq.location.x==qx&&qq.location.y==qy){
								//System.out.println(q+" conflicts with "+qq+"in downright");
								//System.out.println(qq);
								score++;
							}
						}
					}
					qx=qx+1;
					qy=qy+1;

				}
				
				qx=q.location.x;
				qy=q.location.y;
		//case 3:upright diagonal
				while(qx>=1&&qy<=this.limit){
					for(Queen qq:this.config){
						if(!q.equal(qq)){
							if(qq.location.x==qx&&qq.location.y==qy){
								//System.out.println(q+" conflicts with "+qq+"in upright");
								score++;
							}
						}
					}
					qx=qx-1;
					qy=qy+1;
				}
				
				qx=q.location.x;
				qy=q.location.y;
		//case 4: downleft diagonal
				while(qx<=this.limit&&qy>=1){
					for(Queen qq:this.config){
						if(!q.equal(qq)){
							if(qq.location.x==qx&&qq.location.y==qy){
								//System.out.println(q+" conflicts with "+qq+"in downleft");
								score++;
							}
						}
					}
					qx=qx+1;
					qy=qy-1;
				}
				
				return score;
		
	}
	

	public String toString(){
		
		String str="";
		for(Queen q:this.config){
			str+="-"+q.toString();
		}
		return str;
	}
	
}
