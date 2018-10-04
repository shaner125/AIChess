import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that 
	has been coded is a white pawn...a lot done, more to do!
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
    int startX;
    int startY;
    int initialX;
    int initialY;
    JPanel panels;
    JLabel pieces;
    int turnCount = 1;


    public ChessProject() {
        Dimension boardSize = new Dimension(600, 600);

        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane 
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground(i % 2 == 0 ? Color.white : Color.gray);
            else
                square.setBackground(i % 2 == 0 ? Color.gray : Color.white);
        }

        // Setting up the Initial Chess board.
        for (int i = 8; i < 16; i++) {
            pieces = new JLabel(new ImageIcon("WhitePawn.png"));
            panels = (JPanel) chessBoard.getComponent(i);
            panels.add(pieces);
        }
        pieces = new JLabel(new ImageIcon("WhiteRook.png"));
        panels = (JPanel) chessBoard.getComponent(0);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
        panels = (JPanel) chessBoard.getComponent(1);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
        panels = (JPanel) chessBoard.getComponent(6);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteBishop.png"));
        panels = (JPanel) chessBoard.getComponent(2);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteBishop.png"));
        panels = (JPanel) chessBoard.getComponent(5);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKing.png"));
        panels = (JPanel) chessBoard.getComponent(3);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
        panels = (JPanel) chessBoard.getComponent(4);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteRook.png"));
        panels = (JPanel) chessBoard.getComponent(7);
        panels.add(pieces);
        for (int i = 48; i < 56; i++) {
            pieces = new JLabel(new ImageIcon("BlackPawn.png"));
            panels = (JPanel) chessBoard.getComponent(i);
            panels.add(pieces);
        }
        pieces = new JLabel(new ImageIcon("BlackRook.png"));
        panels = (JPanel) chessBoard.getComponent(56);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKnight.png"));
        panels = (JPanel) chessBoard.getComponent(57);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKnight.png"));
        panels = (JPanel) chessBoard.getComponent(62);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackBishop.png"));
        panels = (JPanel) chessBoard.getComponent(58);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackBishop.png"));
        panels = (JPanel) chessBoard.getComponent(61);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKing.png"));
        panels = (JPanel) chessBoard.getComponent(59);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackQueen.png"));
        panels = (JPanel) chessBoard.getComponent(60);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackRook.png"));
        panels = (JPanel) chessBoard.getComponent(63);
        panels.add(pieces);
    }

    //This method returns the name of the piece on a particular square, if one is present.
    private String returnName(int xPos, int yPos) {
        Component c1 = chessBoard.findComponentAt(xPos, yPos);
        if (c1 instanceof JLabel) {
            JLabel awaitingPiece = (JLabel) c1;
            return awaitingPiece.getIcon().toString();
        } else if (c1 == null) {
            return "null";
        }
        return "";
    }

    /*
        This method checks if there is a piece present on a particular square.
    */
    private Boolean piecePresent(int x, int y) {
        Component c = chessBoard.findComponentAt(x, y);
        if (c instanceof JPanel) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean knightCheck(int xPos, int yPos, String opponentColour){
        Boolean inCheck = false;
        for(int i = -75; i<=75; i+=150){
            for(int j=-75;j<=75;j+=150){
                if(returnName(xPos+i,yPos+(j*2)).contains(opponentColour+"Knight")){
                    inCheck = true;
                    System.out.println("king would be in check!");
                }
                else if(returnName(xPos+(i*2),yPos+j).contains(opponentColour+"Knight")){
                    inCheck = true;
                    System.out.println("king would be in check!");
                }
            }

        }
        return inCheck;
    }

    private Boolean straightCheck(int xPos, int yPos, String opponentColour) {
        Boolean inCheck = false;
        for (int i = -75; i <= 75; i += 150) {
            int newXPos = xPos;
            int newYPos = yPos;

            //horizontal check
            String pieceName = "";
            while (pieceName.isEmpty()) {
                newXPos += i;
                pieceName = returnName(newXPos, yPos);
            }
            if (pieceName.contains(opponentColour + "Rook") || pieceName.contains(opponentColour + "Queen")) {
                inCheck = true;
                System.out.println("King would be in check!");
            }
            //Vertical check
            pieceName = "";
            while (pieceName.isEmpty()) {
                newYPos += i;
                pieceName = returnName(xPos, newYPos);
            }
            if (pieceName.contains(opponentColour + "Rook") || pieceName.contains(opponentColour + "Queen")) {
                inCheck = true;
                System.out.println("King would be in check!");
            }
        }
        return inCheck;
    }

    private Boolean diagonalCheck(int xPos, int yPos, String opponentColour){
        Boolean inCheck = false;
        int newYPos = yPos;
        int newXPos = xPos;
        for (int i = -75; i <= 75; i +=150) {
            for (int j = -75; j <= 75; j +=150) {
                String pieceName = "";
                while (pieceName.isEmpty()) {
                    newXPos += i;
                    newYPos += j;
                    pieceName = returnName(newXPos, newYPos);
                }
                if (pieceName.contains(opponentColour + "Pawn") && (j == (opponentColour.equals("White") ? -75 : 75 )) && (Math.abs(newYPos-yPos) == 75)){
                    inCheck = true;
                    System.out.println("king would be in check!");
                }

                else if (pieceName.contains(opponentColour + "Bishop") || pieceName.contains(opponentColour + "Queen")) {
                    inCheck = true;
                    System.out.println("king would be in check!");
                }
                newYPos = yPos;
                newXPos = xPos;
            }
        }


        return inCheck;
    }

    private Boolean isKingInCheck(int xPos, int yPos, String kingColour, String opponentColour) {
        Boolean inCheck = false;
        if(knightCheck(xPos,yPos,opponentColour) || straightCheck(xPos,yPos,opponentColour) || diagonalCheck(xPos,yPos,opponentColour)){
            inCheck = true;
        }
        return inCheck;
    }

    private Boolean checkForOpponentKing(String pieceColour, int newX, int newY) {
        Boolean opponentKing = false;
        for (int i = -75; i <= 75; i = i + 75) {
            for (int j = -75; j <= 75; j = j + 75) {
                if ((((newX + i) > 0 && (newX + i) < 600)) && (((newY + j) > 0) && (newY + j) < 600)) {
                    if (piecePresent(newX + i, newY + j) && checkOpponent(pieceColour, newX + i, newY + j)) {
                        String tmp1 = returnName(newX + i, newY + j);
                        if ((tmp1.contains("King"))) {
                            opponentKing = true;
                        }
                    }
                }
            }
        }
        return opponentKing;
    }

    private Boolean checkOpponent(String colour, int newX, int newY) {
        Boolean opponent;
        String opponentColour = (colour.equals("Black")) ? "White" : "Black";
        if (opponentColour.equals(returnName(newX, newY).substring(0, 5))) {
            opponent = true;
        } else {
            opponent = false;
        }
        return opponent;
    }

    public boolean checkPathIsClear(int xMovement, int yMovement, int startX, int startY, int landingX, int landingY) {
        boolean isClear = true;

        int XDirection = ((landingX - startX) < 0 ? -75 : 75);
        int YDirection = ((landingY - startY) < 0 ? -75 : 75);

        if (xMovement == yMovement) {
            for (int i = 0; i < xMovement; i++) {
                if (piecePresent(startX, startY)) {
                    isClear = false;
                }
                startX = startX + XDirection;
                startY = startY + YDirection;
            }
        } else if (xMovement > 0 && yMovement == 0) {
            for (int i = 0; i < xMovement; i++) {
                if (piecePresent(startX, startY)) {
                    isClear = false;
                }
                startX = startX + XDirection;
            }
        } else if (xMovement == 0 && yMovement > 0) {
            for (int i = 0; i < yMovement; i++) {
                if (piecePresent(startX, startY)) {
                    isClear = false;
                }
                startY = startY + YDirection;
            }
        }
        return isClear;
    }

    public boolean completeMove(int xPos, int yPos, String pieceColour) {
        boolean validMove;
        if (piecePresent(xPos, yPos)) {
            if (checkOpponent(pieceColour, xPos, yPos)) {
                if(returnName(xPos,yPos).contains("King")) {
                    checkmate(pieceColour);
                    validMove = true;
                }
                else{
                    validMove = true;
                }
            } else {
                validMove = false;
            }
        } else {
            validMove = true;
        }
        return validMove;
    }

    public void checkmate(String winner){
        JOptionPane.showMessageDialog(null,winner+" has won the game!");
        System.exit(1);
    }

    /*
        This method is called when we press the Mouse. So we need to find out what piece we have
        selected. We may also not have selected a piece!
    */
    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel)
            return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel) c;
        initialX = e.getX();
        initialY = e.getY();
        startX = (e.getX() / 75);
        startY = (e.getY() / 75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    /*
       This method is used when the Mouse is released...we need to make sure the move was valid before
       putting the piece back on the board.
   */
    public void mouseReleased(MouseEvent e) {
        if (chessPiece == null) return;

        chessPiece.setVisible(false);
        Boolean success = false;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        String tmp = chessPiece.getIcon().toString();
        String pieceName = tmp.substring(0, (tmp.length() - 4));
        Boolean validMove = false;

        int landingX = (e.getX() / 75);
        int landingY = (e.getY() / 75);
        int xMovement = Math.abs((e.getX() / 75) - startX);
        int yMovement = Math.abs((e.getY() / 75) - startY);
        int x = e.getX();
        int y = e.getY();
        String currentColour = pieceName.substring(0, 5);
        String opponentColour = (currentColour.equals("Black")) ? "White" : "Black";
        String currentTurn = (turnCount%2==0) ? "Black" : "White";



        if ((((landingX < 0) || (landingX > 7)) || ((landingY < 0) || landingY > 7)) || !pieceName.contains(currentTurn)) {
            validMove = false;
        } else {

            if (pieceName.contains("King")) {
                if ((xMovement == 1 && yMovement == 0 || yMovement == 1 && xMovement == 0) || (xMovement == 1 && yMovement == 1)) {
                    if (checkForOpponentKing(currentColour, x, y) || isKingInCheck(x, y, currentColour, opponentColour)) {
                        validMove = false;
                    } else if (completeMove(x, y, currentColour)) {
                        validMove = true;
                    }
                }
            }


            if (pieceName.contains("Queen")) {
                if (xMovement == yMovement || xMovement > 0 && yMovement == 0 || xMovement == 0 && yMovement > 0) {
                    if (checkPathIsClear(xMovement, yMovement, initialX, initialY, x, y)) {
                        if (completeMove(x, y, currentColour)) {
                            validMove = true;
                        }
                    }
                }
            }

            if (pieceName.contains("Rook")) {
                if (xMovement > 0 && yMovement == 0 || xMovement == 0 && yMovement > 0) {
                    if (checkPathIsClear(xMovement, yMovement, initialX, initialY, x, y)) {
                        if (completeMove(x, y, currentColour)) {
                            validMove = true;
                        }
                    }
                }
            }

            if (pieceName.contains("Bishop")) {
                if (xMovement == yMovement && xMovement > 0) {
                    if (checkPathIsClear(xMovement, yMovement, initialX, initialY, x, y)) {
                        if (completeMove(x, y, currentColour)) {
                            validMove = true;
                        }
                    }
                }
            }


            if (pieceName.contains("Knight")) {
                if (((xMovement == 1) && (yMovement == 2)) || ((xMovement == 2) && (yMovement == 1))) {
                    if (completeMove(x, y, currentColour)) {
                        validMove = true;
                    }
                }
            }

            if (pieceName.equals("BlackPawn")) {
                if (startY == 6) {
                    if (((yMovement == 1) || (yMovement == 2)) && (startY > landingY) && (xMovement == 0)) {
                        if (yMovement == 2) {
                            if ((!piecePresent(x, y)) && (!piecePresent(x, y + 75))) {
                                validMove = true;
                            }
                        } else {
                            if (!piecePresent(x, y)) {
                                validMove = true;
                            }
                        }
                    } else if ((yMovement == 1) && (startY > landingY) && (xMovement == 1) && piecePresent(x, y)) {
                        if (completeMove(x, y, currentColour)) {
                            validMove = true;
                        }
                    }

                } else {
                    if ((yMovement == 1) && (startY > landingY) && (xMovement == 0)) {
                        if (!piecePresent(x, y)) {
                            validMove = true;
                            if (landingY == 0) {
                                success = true;
                            }
                        }
                    } else if ((yMovement == 1) && (startY > landingY) && (xMovement == 1) && piecePresent(x, y)) {
                        if (completeMove(x, y, currentColour)) {
                            validMove = true;
                        }
                        if (landingY == 0) {
                            success = true;
                        }
                    }


                }
            }

            if (pieceName.equals("WhitePawn")) {
                if (startY == 1) {
                    if (((yMovement == 1) || (yMovement == 2)) && (startY < landingY) && (xMovement == 0)) {
                        if (yMovement == 2) {
                            if ((!piecePresent(x, y)) && (!piecePresent(x, y - 75))) {
                                validMove = true;
                            }
                        } else {
                            if (!piecePresent(x, y)) {
                                validMove = true;
                            }
                        }
                    } else if ((yMovement == 1) && (startY < landingY) && (xMovement == 1) && piecePresent(x, y)) {
                        if (completeMove(x, y, currentColour)) {
                            validMove = true;
                        }
                    }
                } else {
                    if ((yMovement == 1) && (startY < landingY) && (xMovement == 0)) {
                        if (!piecePresent(x, y)) {
                            validMove = true;
                            if (landingY == 7) {
                                success = true;
                            }
                        }
                    } else if ((yMovement == 1) && (startY < landingY) && (xMovement == 1) && piecePresent(x, y)) {
                        if (completeMove(x, y, currentColour)) {
                            validMove = true;
                            if (landingY == 7) {
                                success = true;
                            }
                        }
                    }
                }
            }
        }
        if (!validMove) {
            int location = 0;
            if (startY == 0) {
                location = startX;
            } else {
                location = (startY * 8) + startX;
            }
            String pieceLocation = pieceName + ".png";
            pieces = new JLabel(new ImageIcon(pieceLocation));
            panels = (JPanel) chessBoard.getComponent(location);
            panels.add(pieces);
        } else {
            if (success) {
                Component c1 = chessBoard.findComponentAt(x, y);
                JLabel awaitingPiece = (JLabel) c1;
                String tmp1 = awaitingPiece.getIcon().toString();
                if (tmp1.contains("Black")) {
                    int location = 56 + (e.getX() / 75);
                    if (c instanceof JLabel) {
                        Container parent = c.getParent();
                        parent.remove(0);
                        pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                        parent = (JPanel) chessBoard.getComponent(location);
                        parent.add(pieces);
                    } else {
                        Container parent = (Container) c;
                        pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                        parent = (JPanel) chessBoard.getComponent(location);
                        parent.add(pieces);
                    }
                } else if (((tmp1.contains("White")))) {
                    int location = 0 + (e.getX() / 75);
                    if (c instanceof JLabel) {
                        Container parent = c.getParent();
                        parent.remove(0);
                        pieces = new JLabel(new ImageIcon("BlackQueen.png"));
                        parent = (JPanel) chessBoard.getComponent(location);
                        parent.add(pieces);
                    } else {
                        Container parent = (Container) c;
                        pieces = new JLabel(new ImageIcon("BlackQueen.png"));
                        parent = (JPanel) chessBoard.getComponent(location);
                        parent.add(pieces);
                    }
                }
            } else {
                if (c instanceof JLabel) {
                    Container parent = c.getParent();
                    parent.remove(0);
                    parent.add(chessPiece);
                } else {
                    Container parent = (Container) c;
                    parent.add(chessPiece);
                }
                chessPiece.setVisible(true);
            }
            turnCount++;
            currentTurn = (turnCount%2==0?"Black":"White");
            System.out.println("-------------------------------------------------------------------");
            System.out.println("The piece that is being moved is :" + pieceName);
            System.out.println("The starting coordinates are : " + "( " + startX + "," + startY + ")");
            System.out.println("The xMovement is : " + xMovement);
            System.out.println("The yMovement is : " + yMovement);
            System.out.println("The landing coordinates are : " + "( " + landingX + "," + landingY + ")");
            System.out.println("-------------------------------------------------------------------");
            System.out.println(currentTurn+" pieces turn to move.");
        }

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    /*
        Main method that gets the ball moving.
    */
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println("Let the chess begin! White piece gets first move");
    }
}


