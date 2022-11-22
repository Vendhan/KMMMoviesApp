//
//  MoviesScreen.swift
//  iosApp
//
//  Created by Arul Vendhan Arumugam on 29/08/22.
//  Copyright © 2022 orgName. All rights reserved.
//
import SwiftUI
import shared

struct MoviesScreen: View {
    
    @ObservedObject private var viewmodel: MoviesScreenVM = MoviesScreenVM()
        
    var body: some View {
        
        NavigationView {
            ZStack(){
                
                switch true {
                    
                case viewmodel.nowPlayingMoviesState.error.isError:
                    Text(viewmodel.nowPlayingMoviesState.error.errorMessage)
                    
                case viewmodel.nowPlayingMoviesState.isSuccess:
                    
                    ScrollView(showsIndicators: false) {
                        
                        LazyVStack(alignment: HorizontalAlignment.leading) {
                            if(!viewmodel.nowPlayingMoviesState.movies.isEmpty) {
                                NowPlayingMoviesContent(title: "Now Playing Movies", movies: viewmodel.nowPlayingMoviesState.movies)
                            }
                            if(!viewmodel.upcomingMoviesState.movies.isEmpty) {
                                UpcomingMoviesContent(
                                    title: "Upcoming Movies",
                                    movies: viewmodel.upcomingMoviesState.movies
                                )
                            }
                            if(!viewmodel.upcomingMoviesState.movies.isEmpty){
                                TopRatedMoviesContent(title: "Top Rated Movies", movies: viewmodel.topRatedMoviesState.movies)
                            }
                        }
                        .frame( maxWidth: .infinity)
                        .edgesIgnoringSafeArea(.all)
                        .listStyle(PlainListStyle())
                    }
                    
                case viewmodel.nowPlayingMoviesState.isLoading:
                    ProgressView()
                    
                default:
                    Text("default")
                }
            }.onAppear(
                perform:
                    {
                        if(viewmodel.status) {
                            viewmodel.viewModel.onIntent(intent: MoviesScreenSideEffects.GetNowPlayingMovies())
                            viewmodel.viewModel.onIntent(intent: MoviesScreenSideEffects.GetUpcomingMovies())
                            viewmodel.viewModel.onIntent(intent: MoviesScreenSideEffects.GetTopRatedMovies())
                            viewmodel.status = false
                        }
                    })
                .navigationTitle(Text("ShowTime"))
                .navigationBarTitleDisplayMode(NavigationBarItem.TitleDisplayMode.inline)
        }
    }
}

struct Screen_Previews: PreviewProvider {
    static var previews: some View {
        MoviesScreen()
    }
}
