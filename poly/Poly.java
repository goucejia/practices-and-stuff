/**
 * Created by fang on 11/30/18.
 */

public class Poly {
    // nested class Term
     class Term {
        private int coef;
        private int expo;
        private Term next;

        private Term(int coef, int expo) {
            this.coef = coef;
            this.expo = expo;
            this.next = null;
        }

        public int getCoef() {
            return coef;
        }

        public void setCoef(int coef) {
            this.coef = coef;
        }

        public int getExpo() {
            return expo;
        }

        public void setExpo(int expo) {
            this.expo = expo;
        }

        public Term getNext() {
            return next;
        }

        public void setNext(Term next) {
            this.next = next;
        }
    }

    private Term first;
    private Term last;

    public Poly() {
        Term temp_first = new Term(0, Integer.MAX_VALUE);
        this.setFirst(temp_first);
        this.setLast(temp_first);
    }

    public Term getFirst() {
        return first;
    }

    public void setFirst(Term first) {
        this.first = first;
    }

    public Term getLast() {
        return last;
    }

    public void setLast(Term last) {
        this.last = last;
    }

    // helper function, append term to poly
    private Poly append_term(Term t) {
        Term cur_term = this.getLast();
        cur_term.setNext(t);
        this.setLast(t);
        return this;
    }

    // Test if this is the zero polynomial
    public boolean isZero() {
        if (this.first.coef == 0 && this.last.coef == 0 && this.first.next == null) {
            return true;
        } else {
            return false;
        }
//        return (this.first.coef == 0 && this.last.coef == 0 && this.first.next == null);
    }

    public Poly plus(int coef, int expo) {
        if (this.isZero()) {
            Term temp_term = new Term(coef, expo);
            this.setFirst(temp_term);
            this.setLast(temp_term);
        } else {
            int last_expo = this.last.getExpo();
            if (coef == 0 || expo < 0 || expo > last_expo) {
                throw new IllegalArgumentException();
            } else {
                Term temp_term = new Term(coef, expo);
                this.append_term(temp_term);
//                Term last = this.getLast();
//                last.setNext(temp_term);
//                this.setLast(temp_term);
            }
        }
        return this;
    }

    // add poly (this) and (that)
    public Poly plus(Poly that) {
        Poly result = new Poly();
        Term left;
        Term right;
        if (this.isZero()) {
            left = null;
        } else {
            left = this.getFirst();
        }

        if (that.isZero()) {
            right = null;
        } else {
            right = that.getFirst();
        }

        while (left != null && right != null) {
            if (left.getExpo() > right.getExpo()) {
                Term copy_left = new Term(left.getCoef(), left.getExpo());
                result.append_term(copy_left);
                left = left.getNext();
            } else if (right.getExpo() > left.getExpo()) {
                Term copy_right = new Term(right.getCoef(), right.getExpo());
                result.append_term(copy_right);
                right = right.getNext();
            } else {
                int expo = left.getExpo();
                int coef = left.getCoef() + right.getCoef();
                Term temp_term = new Term(coef, expo);
                result.append_term(temp_term);
                left = left.getNext();
                right = right.getNext();
            }
        }

        return result;
    }


//    public String toString() {
//
//    }

}
