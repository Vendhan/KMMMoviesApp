//
//  UpcomingMoviesContent.swift
//  iosApp
//
//  Created by Arul Vendhan Arumugam on 01/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct UpcomingMoviesContent: View {
    
    var title : String
    var movies : [MoviesResult] = []
    
    init(title : String, movies : [MoviesResult]){
        self.title = title
        self.movies = movies
    }
    
    var body: some View {
        MoviesHeader(title: title)
        ScrollView(.horizontal, showsIndicators: false) {
            
            LazyHStack(alignment: VerticalAlignment.top, spacing: 16) {
                ForEach(movies, id: \.id) { movie in
                    NavigationLink(destination: DetailsScreen(movieID: Int(movie.id))) {
                        MoviesCard(movie: movie)
                    }
                    .buttonStyle(PlainButtonStyle())
                }
            }
            .padding(EdgeInsets(top: 0, leading: 16, bottom: 0, trailing: 0))
        }
    }
}
