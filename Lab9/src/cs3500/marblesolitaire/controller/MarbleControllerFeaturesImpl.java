package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;

/**
 * This class represents the marble solitaire controller and implements
 * the ControllerFeatures interface.
 */
public class MarbleControllerFeaturesImpl implements ControllerFeatures {
  private MarbleSolitaireModel model;
  private MarbleSolitaireGuiView view;
  private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
  private int[] fromCell;

  public MarbleControllerFeaturesImpl(MarbleSolitaireModel model, MarbleSolitaireGuiView view) {
    this.model = model;
    this.view = view;
    this.fromCell = new int[2];
  }

  @Override
  public void play(int row, int col) {
    if (!this.model.isGameOver()) {
      if (this.model.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Marble) {
        this.fromCell[0] = row;
        this.fromCell[1] = col;
        return;
      } else if (this.model.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Empty
      && fromCell[0] != -1) {
        try {
          this.model.move(this.fromCell[0], this.fromCell[1], row, col);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("Invalid move. Play Again.");
        }
        this.view.refresh();
        fromCell[0] = -1;
        fromCell[1] = -1;
      } else {
        this.view.renderMessage("Game over!");
      }
    }
  }
}
