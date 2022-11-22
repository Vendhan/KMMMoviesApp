//
//  DetailsScreenVM.swift
//  iosApp
//
//  Created by Arul Vendhan Arumugam on 02/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

public class DetailsScreenVM : ObservableObject {
    
    var viewModel : DetailsViewModel
    
    /**
     * state flow acts as a state for swift ui here
     */
    @Published private(set) var movieState: DetailsScreenState
    
    /**
     *fusing the .asObserveable extension funstion we get the wrapped viewmodel and the stateflow
     */
    init() {
        viewModel = ViewModels().getDetailsViewModel()
        
        self.movieState = viewModel.movieState.value as! DetailsScreenState
        
        (viewModel.movieState.asPublisher() as AnyPublisher<DetailsScreenState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$movieState)
    }
}
