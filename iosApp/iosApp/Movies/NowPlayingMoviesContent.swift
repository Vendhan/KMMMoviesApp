//
//  NowPlayingMoviesContent.swift
//  iosApp
//
//  Created by Arul Vendhan Arumugam on 31/08/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NowPlayingMoviesContent: View {
    
    var title : String
    var movies : [MoviesResult]
    // var onClick: (Int) -> ()
    
    
    var body: some View {
        MoviesHeader(title: title)
        ScrollView(.horizontal, showsIndicators: false) {
            
            LazyHStack(alignment: VerticalAlignment.top, spacing: 16) {
                ForEach(movies, id: \.id) { movie in
                    NavigationLink(destination: DetailsScreen(movieID: Int(movie.id))) {
                        MoviesCard(movie: movie)
                    }
                    .buttonStyle(PlainButtonStyle())
//                    Button(action: {
//                        onClick(Int(movie.id))
//                    }) {
//                        MoviesCard(movie: movie)
//                    }
                }
            }
            .padding(EdgeInsets(top: 0, leading: 16, bottom: 0, trailing: 0))
        }
    }
}
