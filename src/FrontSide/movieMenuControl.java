package FrontSide;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import Data.MainMovieData;
import Data.WebCrawerTools;
import Model.MovieMenu;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * ����ӰƬѡ�����<br>
 * ԭ����Sub1Control
 * @version 2018/5/27
 * 
 * @author ��˼��
 *
 * **/
public class movieMenuControl  {
	@FXML private AnchorPane sub1Pane;
	@FXML private AnchorPane subAnchorPane1;
	@FXML private AnchorPane subAnchorPane2;
	@FXML private ScrollPane scrollPane;
	@FXML private GridPane gridPane;
	@FXML private ImageView movieInformImage;
	@FXML private ImageView imageView1;
	@FXML private ImageView imageView2;
	@FXML private ImageView imageView3;
	@FXML private ImageView imageView4;
	@FXML private ImageView imageView5;
	
	@FXML private Button g00; //ȫ��
	@FXML private Button g03; //����
	@FXML private Button g13; //�ƻ�
	@FXML private Button g01; //ϲ��
	@FXML private Button g02; //����
	@FXML private Button g04; //����
	@FXML private Button g05; //�ֲ�
	@FXML private Button g06; //����
	@FXML private Button g07; //��ʷ
	@FXML private Button g11; //����
	@FXML private Button g12; //ð��
	@FXML private Button g10; //��ͯ
	@FXML private Button g14; //�й�
	@FXML private Button g15; //ŷ��
	@FXML private Button g16; //�պ�
	@FXML private Button g17; //����
	
	@FXML private Button right;
	@FXML private Button left;
	@FXML private Button nowPlay;
	@FXML private Button backToMenu;
	@FXML private Text movieCountry;
	@FXML private Text movieName; 
	@FXML private Text movieYear;
	@FXML private Text movieInform;
	
	private MovieMenu backSide = new MovieMenu();
	private int y = 0;
	private int x = 0;
	
	private String focusedCss1 = getClass().getResource("css\\CSS gridPaneButton1.css").toExternalForm();
	private String focusedCss2 = getClass().getResource("css\\CSS gridPaneButton2.css").toExternalForm();
	
	private String[][] Types = {{"ȫ��", "ϲ��", "����", "����", "����", "�ֲ�", "����", "��ʷ"},
		       {"��ͯ", "����", "ð��", "�ƻ�", "�й�", "ŷ��", "�պ�", "����"}};	

	private EventHandler<KeyEvent> gridPaneHandler = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent key) {
			// TODO Auto-generated method stub
			System.out.println(x+" "+y);
			
			
			if(key.getCode() == KeyCode.LEFT){
				if(x<=7&&x>-1) {
					x--;
				}
				key.consume();
				String buttonId = "g"+y+""+x;
				System.out.println(buttonId);
				for(int i = 0 ;i < 16 ; i ++) {
					Button but = (Button)gridPane.getChildren().get(i);
					but.getStylesheets().clear();
					if(but.getId().equals(buttonId)) {
						but.getStylesheets().add(focusedCss2);
					}
				}
			}
			if(key.getCode() == KeyCode.RIGHT){
				if(x<7&&x>=-1) {
					x++;
				}
				key.consume();
				String buttonId = "g"+y+""+x;
				System.out.println(buttonId);
				for(int i = 0 ;i < 16 ; i ++) {
					Button but = (Button)gridPane.getChildren().get(i);
					but.getStylesheets().clear();
					if(but.getId().equals(buttonId)) {
						but.getStylesheets().add(focusedCss2);
					}
				}
			}
		    if(key.getCode() == KeyCode.UP){
		    	if(y==1) {
		    		y--;
		    	}
				key.consume();
				String buttonId = "g"+y+""+x;
				System.out.println(buttonId);
				for(int i = 0 ;i < 16 ; i ++) {
					Button but = (Button)gridPane.getChildren().get(i);
					but.getStylesheets().clear();
					if(but.getId().equals(buttonId)) {
						but.getStylesheets().add(focusedCss2);
					}
				}
			}
		    if(key.getCode() == KeyCode.DOWN){
		        y++;
				key.consume();
				String buttonId = "g"+y+""+x;
				System.out.println(buttonId);
				for(int i = 0 ;i < 16 ; i ++) {
					Button but = (Button)gridPane.getChildren().get(i);
					but.getStylesheets().clear();
					if(but.getId().equals(buttonId)) {
						but.getStylesheets().add(focusedCss2);
					}
				}
			}
			//�����л���ͬ���͵�Ӱ
		    if(key.getCode() == KeyCode.ENTER){
		    	
		    	for(int i = 0 ;i < 16 ; i ++) {
					Button but = (Button)gridPane.getChildren().get(i);
					but.getStylesheets().clear();
					if(but.getId().equals("g"+y+""+x)) {
						but.getStylesheets().add(focusedCss1);
					}
				}
		    	
		    	if(y == 0 && x == 0){
					clickall();
					key.consume();
					subAnchorPane1.requestFocus();
					System.out.println("all movie");
				}
				else{
					backSide.selectTypeAction(Types[y][x]);
					key.consume();
					subAnchorPane1.requestFocus();
					System.out.println("type movie");
				}
		    	
			}
			//������������1���뵱�����Ƶ�subAnchorPane1�ϣ����Լ��������ƶ���Ӱ��������
			if(y > 1){
				
				for(int i = 0 ;i < 16 ; i ++) {
					Button but = (Button)gridPane.getChildren().get(i);
					but.getStylesheets().clear();
				}
				
				subAnchorPane1.requestFocus();
				System.out.println("subAnchorPane1 is focused?" + subAnchorPane1.isFocused());
				subAnchorPane1.removeEventHandler(KeyEvent.KEY_PRESSED, gridPaneHandler);
			    y--;
				key.consume();
			}
		}  
	};
	 
    private EventHandler<KeyEvent> anchorPaneHandler = new EventHandler<KeyEvent>() {


		@Override
		public void handle(KeyEvent key) {
			// TODO Auto-generated method stub
			if(key.getCode() == KeyCode.RIGHT){
				backSide.rightAction();
				key.consume();
				System.out.println("right1");
			}
			if(key.getCode() == KeyCode.LEFT){
				backSide.leftAction();
				key.consume();
				System.out.println("left1");
			}
			//�����Ӱ��Ϣ����
			if(key.getCode() == KeyCode.ENTER){
				clickView3();
				key.consume();
				System.out.println("enter infomation");
			}
			//�����൱�ڹ���Ƶ�gridPane��
			if(key.getCode() == KeyCode.UP){
				gridPane.requestFocus();
				System.out.println("gridPane is focused?" + gridPane.isFocused());
				gridPane.addEventHandler(KeyEvent.KEY_PRESSED, gridPaneHandler);
				
				for(int i = 0 ;i < 16 ; i ++) {
					Button but = (Button)gridPane.getChildren().get(i);
					but.getStylesheets().clear();
					if(but.getId().equals("g"+y+""+x)) {
						but.getStylesheets().add(focusedCss2);
					}
				}
				
				key.consume();
			
			}
		}
    	
    };	
    
    private EventHandler<KeyEvent> informationHandler	= new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent key) {
			// TODO Auto-generated method stub
			//����
			if(key.getCode() == KeyCode.BACK_SPACE){
				clickBackToMenu();
				subAnchorPane1.requestFocus();
				key.consume();
			}
			//����
			if(key.getCode() == KeyCode.ENTER){
				clickNowPlay();
				key.consume();
			}
		}
	
    };
    
    /**ǰ�˳�ʼ���������У�**/
    @FXML
    public void initialize() {
    	
    	System.out.println("MOVIE_MENU_CONTROL-INITIALIZED");
        
    	subAnchorPane1.setVisible(true);
		subAnchorPane2.setVisible(false);
		//�����л���Ӱ
		subAnchorPane1.addEventHandler(KeyEvent.KEY_PRESSED, anchorPaneHandler);
		//��Ӱ��Ϣ����
		subAnchorPane2.addEventHandler(KeyEvent.KEY_PRESSED, informationHandler);
		
		subAnchorPane1.requestFocus();
		Button but = (Button)gridPane.getChildren().get(0);
		but.getStylesheets().clear();
		but.getStylesheets().add(focusedCss1);
		
    }
	
    /**ʹ�۽�**/
    public void subAnchorPane1Focus() {
    	subAnchorPane1.requestFocus();
    }
    
    /**�����ҵĺ��**/
    public MovieMenu getBackSide() {
    	
    	return backSide;
    
    }
	
    /**��˳�ʼ��������**/
    public void backSideStart(PlayerControl playerControl,movieMenuControl frontSide 
                                ,AnchorPane centrePane   ,     Parent mediaPlayerRoot) {
    	
    	backSide.setFatherObject(playerControl, frontSide, centrePane, mediaPlayerRoot);
    	backSide.showPicInitial();
    
    }
    
    /**���ӰƬѡ�����Ĳ���ͼ��**/
	public void initialClear() {
		
		imageView1.setImage(null);
		imageView2.setImage(null);
		imageView3.setImage(null);
		imageView4.setImage(null);
		imageView5.setImage(null);
	
	}
	
	/**ˢ��gridPane**/
	private void gridPaneRefresh(int x, int y) {
		for(int i = 0 ;i < 16 ; i ++) {
			Button but = (Button)gridPane.getChildren().get(i);
			but.getStylesheets().clear();
			if(but.getId().equals("g"+y+""+x)) {
				but.getStylesheets().add(focusedCss2);
			}
		}
		this.x = x;
		this.y = y;
	}
	
	/**��ʾ��Ӱ��Ϣ**/
	public void showInform(String [] informList) {
		
		movieName.setText(informList[0]);
		movieYear.setText(informList[1]);
		movieCountry.setText(informList[2]);
		movieInform.setText(informList[3]);
	
	}

	/**�ı�����**/
	public void changeLanguage(Properties pps,String language) {
		System.out.println("Change language to "+language);
		
		if(language.equals("ENGLISH")) {
			
			for(int i = 0;i<gridPane.getChildren().size();i++) {
				Button but = (Button)gridPane.getChildren().get(i);
				but.setText(pps.getProperty(but.getText()));
			}
			
			nowPlay.setText(pps.getProperty("��������"));
			backToMenu.setText(pps.getProperty("����"));
		
		}else if(language.equals("CHINESE")) {
			
			for(int i = 0;i<gridPane.getChildren().size();i++) {
				Button but = (Button)gridPane.getChildren().get(i);
	            Set<Entry<Object, Object>> entries = pps.entrySet();
				
	            for (Entry entry : entries) {
					
	            	if (but.getText().equals(entry.getValue())) {
						but.setText(entry.getKey().toString());break;
					}  
				}
			}
			
			nowPlay.setText(pps.getProperty("��������"));
			backToMenu.setText(pps.getProperty("����"));
		
		}	
	}
	
	/**��ѡ�е�ͼƬ������Ӱ��Ϣ����Ĵ�ͼ**/
    public void deliverImage(int id) {
    	
    	Image image = null;
        switch(id) {
        default :;
        case 1: image = imageView1.getImage();break;
        case 2: image = imageView2.getImage();break;
        case 3: image = imageView3.getImage();break;
        case 4: image = imageView4.getImage();break;
        case 5: image = imageView5.getImage();break;
        }
        movieInformImage.setImage(image);
    
    }
	
    /**��ָ��id��λ����ʾͼƬ**/
	public void showPic(String picUrl,int id,boolean error,String language) {
		
        if(error==true) {
        	Image img = null;
        	if(language.equals("CHINESE")) {
        		img = new Image(movieMenuControl.class.getResourceAsStream("frontSidePicture\\noMovie(CN).jpg"));
        	}else if(language.equals("ENGLISH")){
        		img = new Image(movieMenuControl.class.getResourceAsStream("frontSidePicture\\noMovie(EN).jpg"));
        	}
        	imageView3.setImage(img);
        	imageView1.setImage(null);
        	imageView2.setImage(null);
        	imageView4.setImage(null);
        	imageView5.setImage(null);
        	
        	
		}else {
		    Image img = new Image(movieMenuControl.class.getResourceAsStream("moviePicture\\"+picUrl));
		    System.out.println(img.getHeight()+"   "+img.getWidth());
		    System.out.println("img"+imageView1);
		
		    switch(id) {
		    default: ;
		    case 1:imageView1.setImage(img);break;
		    case 2:imageView2.setImage(img);break;
		    case 3:imageView3.setImage(img);break;
		    case 4:imageView4.setImage(img);break;
		    case 5:imageView5.setImage(img);break;
		    }
		}
	
	}
    
	@FXML
    public void clickRight() {
    	backSide.rightAction();
    }
	@FXML
	public void clickLeft() {
		backSide.leftAction();
	}
	
	@FXML 
	public void clickBackToMenu() {
		subAnchorPane2.setVisible(false);
		subAnchorPane1.setVisible(true);
		backSide.backToMenu();
	}
	
	@FXML
	public void clickNowPlay() {
		subAnchorPane2.setVisible(false);
		subAnchorPane1.setVisible(true);
	    backSide.moviePlay();
	}
	
	
	@FXML
	public void clickView1() {
		subAnchorPane1.setVisible(false);
        subAnchorPane2.setVisible(true);
        backSide.deliverImage(1);
	}
	@FXML
	public void clickView2(){
		subAnchorPane1.setVisible(false);
        subAnchorPane2.setVisible(true);
        backSide.deliverImage(2);
	}
	@FXML
	public void clickView3(){
		subAnchorPane1.setVisible(false);
        subAnchorPane2.setVisible(true);
        backSide.deliverImage(3);
	}
	@FXML
	public void clickView4(){
		subAnchorPane1.setVisible(false);
        subAnchorPane2.setVisible(true);
        backSide.deliverImage(4);
	}
	@FXML
	public void clickView5(){
		subAnchorPane1.setVisible(false);
        subAnchorPane2.setVisible(true);
        backSide.deliverImage(5);
	}
	
	@FXML
	public void clickall() { backSide.clickAll();}
	@FXML
	public void clickSciencefiction() { 
		backSide.selectTypeAction("�ƻ�");
		gridPaneRefresh(3,1);
	}
	
	@FXML
	public void clickComedy() { 
		backSide.selectTypeAction("ϲ��");
		gridPaneRefresh(1,0);
	}
	@FXML
	public void clickTragedy() { 
		backSide.selectTypeAction("����");
		gridPaneRefresh(2,0);
	}
	@FXML
	public void clicklove() { 
		backSide.selectTypeAction("����");
		gridPaneRefresh(3,0);
	}
	@FXML
	public void clickAction() { 
		backSide.selectTypeAction("����");
		gridPaneRefresh(4,0);
	}	
	@FXML
	public void clickHorrible() { 
		backSide.selectTypeAction("�ֲ�");
		gridPaneRefresh(5,0);
	}	
	@FXML
	public void clickSuspense() { 
		backSide.selectTypeAction("����");
		gridPaneRefresh(6,0);
	}	
	@FXML
	public void clickHistory() { 
		backSide.selectTypeAction("��ʷ");
		gridPaneRefresh(7,0);
	}	
	@FXML
	public void clickMusic() { 
		backSide.selectTypeAction("����");
		gridPaneRefresh(1,1);
	}	
	@FXML
	public void clickAdventure() { 
		backSide.selectTypeAction("ð��");
		gridPaneRefresh(2,1);
	}	
	@FXML
	public void clickChild() { 
		backSide.selectTypeAction("��ͯ");
		gridPaneRefresh(0,1);
	}
	@FXML
	public void clickChinese() { 
		backSide.selectTypeAction("�й�");
		gridPaneRefresh(4,1);
	}
	@FXML
	public void clickEUUS() { 
		backSide.selectTypeAction("ŷ��");
		gridPaneRefresh(5,1);
	}
	@FXML
	public void clickJPKA() { 
		backSide.selectTypeAction("�պ�");
		gridPaneRefresh(6,1);
	}
	@FXML
	public void clickOther() { 
		backSide.selectTypeAction("����");
		gridPaneRefresh(7,1);
	}
}