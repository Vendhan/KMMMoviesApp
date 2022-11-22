//
//  MoviesHeader.swift
//  iosApp
//
//  Created by Arul Vendhan Arumugam on 01/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct MoviesHeader: View {
    
    var title : String
    
    init(title : String) {
        self.title = title
    }
    var body: some View {
        
        Text("\(title)")
            .padding(16)
            .font(.system(size: 18, weight: .bold))
        
    }
}
