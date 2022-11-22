//
//  MoviesScreenVM.swift
//  iosApp
//
//  Created by Arul Vendhan Arumugam on 02/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//
import Foundation
import Combine
import shared

class MoviesScreenVM : ObservableObject {
    
    var viewModel : MoviesViewModel
    
    /**
     * state flow acts as a state for swift ui here
     */
    @Published private(set) var nowPlayingMoviesState: MoviesScreenState
    
    @Published private(set) var upcomingMoviesState: MoviesScreenState
    
    @Published private(set) var topRatedMoviesState: MoviesScreenState
    
    var status: Bool = true
    
    /**
     *fusing the .asObserveable extension funstion we get the wrapped viewmodel and the stateflow
     */
    
    init() {
        viewModel = ViewModels().getMoviesViewModel()

        self.nowPlayingMoviesState = viewModel.nowPlayingMoviesState.value as! MoviesScreenState
        
        self.upcomingMoviesState = viewModel.upcomingMoviesState.value as! MoviesScreenState
        
        self.topRatedMoviesState = viewModel.topRatedMoviesState.value as! MoviesScreenState
        
        (viewModel.nowPlayingMoviesState.asPublisher() as AnyPublisher<MoviesScreenState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$nowPlayingMoviesState)
        
        (viewModel.upcomingMoviesState.asPublisher() as AnyPublisher<MoviesScreenState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$upcomingMoviesState)
        
        (viewModel.topRatedMoviesState.asPublisher() as AnyPublisher<MoviesScreenState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$topRatedMoviesState)
    }
}
