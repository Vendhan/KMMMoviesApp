//
//  MoviesCard.swift
//  iosApp
//
//  Created by Arul Vendhan Arumugam on 29/08/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared
import CachedAsyncImage

struct MoviesCard: View {
    
    var movie :  MoviesResult
    
    var body: some View {
        VStack (alignment: HorizontalAlignment.leading){
            if #available(iOS 15.0, *) {
                let url = URL(string: movie.posterPath ?? "")
                CachedAsyncImage(
                    url: url,
                    content: { image in
                        image.resizable()
                            .aspectRatio(contentMode: .fill)
                            .frame(maxWidth: 140, maxHeight: 170)
                    },
                    placeholder: {
                        ZStack {
                            Text("Loading..")
                                .font(.system(size: 16, weight: .medium))
                        }
                        .frame(width: 140, height: 170, alignment: Alignment.center)
                    }
                )
            }
            VStack(alignment: HorizontalAlignment.leading) {
                Spacer()
                    .frame(height: 12)
                
                Text(movie.title)
                    .font(.system(size: 14, weight: .bold))
                    .lineLimit(2)
                    .previewDisplayName("Movie Name")
                
                Spacer()
                    .frame(height: 4)
                
                Text(movie.releaseDate)
                    .font(.system(size: 12, weight: .light))
                    .lineLimit(1)
                    .previewDisplayName("")
            }
            .padding(12)
        }
        .frame(width: 140, height: 250)
        .clipShape(RoundedRectangle(cornerRadius: 8))
    }
}
