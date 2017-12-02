/*

 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

 * Tag: array, matrix

 * Author : Dawei Li

 */


#include <vector>
#include <set>

using namespace std;

class Solution {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        
        int m = matrix.size();
        
        if( m == 0  )
        {
            return;
        }
        
        int n = matrix[0].size();
        
        if( n == 0 )
        {
            return;
        }

		m_col0Flag = false;
		for( int i = 0; i<m; i++ )
		{
			if( matrix[i][0] == 0 )
			{
				m_col0Flag = true;
				break;
			}
		}
        
        for( int i = 0; i<m; i++ )
        {
            
            for( int j = 1; j<n; j++ )
            {
                if( matrix[i][j] == 0 )
                {
                    matrix[i][0] = 0;
					matrix[0][j] = 0;
                }
            }
        }

		for( int i = m-1; i>=0; i++ )
        {
            for( int j = 1; j<n; j++ )
            {
                if( matrix[i][0] == 0 || matrix[0][j] == 0 )
                {
                    matrix[i][j] = 0;
                }
            }
        }
		if( m_col0Flag )
		{
			for( int i = 0; i<m; i++ )
			{
				matrix[i][0] = 0;
			}
		}
        
    }
    
private:

    bool m_col0Flag;

};

int main( int argc, char** argv )
{
    int v[] = {1,0};
    vector< int > vec( v, v + sizeof(v)/sizeof(int) );
    vector<vector<int>> matrix;
    matrix.push_back(vec);

    Solution sln;
    sln.setZeroes(matrix);

    return 0;

}
