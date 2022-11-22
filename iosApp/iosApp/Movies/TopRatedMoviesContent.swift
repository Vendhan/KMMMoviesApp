//
//  TopRatedMoviesContent.swift
//  iosApp
//
//  Created by Arul Vendhan Arumugam on 02/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TopRatedMoviesContent: View {
    
    var title : String
    var movies : [MoviesResult] = []
    
    init(title : String, movies : [MoviesResult]){
        self.title = title
        self.movies = movies
    }
    
    var body: some View {
        MoviesHeader(title: title)
        ForEach(movies, id: \.id) { movie in
            NavigationLink(destination: DetailsScreen(movieID: Int(movie.id))) {
                MoviesCardHorizontal(movie: movie)
            }
            .buttonStyle(PlainButtonStyle())
        }
    }
}
