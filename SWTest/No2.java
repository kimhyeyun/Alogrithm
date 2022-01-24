import java.util.List;

public class No2 {
    class person{
        String name;
        int sex;
        person mother;
        person father;
        person couple;
        List<person> children;

        public person(String name, int sex) {
            this.name = name;
            this.sex = sex;
            this.mother = null;
            this.father = null;
            this.couple = null;
            this.children = null;
        }

        public void setMother(person mother) {
            this.mother = mother;
        }

        public void setFather(person father) {
            this.father = father;
        }

        public void setCouple(person couple) {
            this.couple = couple;
        }

        public void addChild(person child){
            this.children.add(child);
        }

        public void addMother(person mother){
            this.mother = mother;
        }
    }
    class UserSolution {
        //	 The below commented methods are for your reference. If you want
        //	 to use it, uncomment these methods.
	/*
		int mstrcmp(char[] a, char[] b) {
			int i;
			for (i = 0; a[i] != '\0'; i++) {
				if (a[i] != b[i])
					return a[i] - b[i];
			}
			return a[i] - b[i];
		}

		int mstrlen(char[] a) {
			int len = 0;

			while (a[len] != '\0')
				len++;

			return len;
		}

		void mstrcpy(char[] dest, char[] src) {
			int i = 0;
			while (src[i] != '\0') {
				dest[i] = src[i];
				i++;
			}
			dest[i] = src[i];
		}
	*/


        void init(char initialMemberName[], int initialMemberSex) {

        }

        boolean addMember(char newMemberName[], int newMemberSex, int relationship, char existingMemberName[]) {

            return false;
        }

        int getDistance(char nameA[], char nameB[]) {

            return -1;
        }

        int countMember(char name[], int dist) {

            return -1;
        }
    }

}
