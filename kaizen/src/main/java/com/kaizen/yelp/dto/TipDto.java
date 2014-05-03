package com.kaizen.yelp.dto;

import java.util.ArrayList;
import java.util.List;

import com.kaizen.yelp.domain.Tip;

public class TipDto {
        private List<Tip> tips = new ArrayList<Tip>();

        public void addTip(Tip tip)
        {
                tips.add(tip);
        }
        /**
     * @return the tips
     */
    public List<Tip> getTips() {
        return tips;
    }

    /**
     * @param tips
     *            the tips to set
     */
    public void setTips(List<Tip> tips) {
        this.tips = tips;
    }
}

