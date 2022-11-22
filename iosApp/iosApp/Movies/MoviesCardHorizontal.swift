//
//  MoviesCardHorizontal.swift
//  iosApp
//
//  Created by Arul Vendhan Arumugam on 02/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//
import SwiftUI
import shared
import CachedAsyncImage

struct MoviesCardHorizontal: View {
    
    var movie :  MoviesResult
    
    var body: some View {
        VStack(alignment: HorizontalAlignment.leading) {
            
            HStack(alignment: VerticalAlignment.top) {
                if #available(iOS 15.0, *) {
                    let url = URL(string: movie.posterPath ?? "")
                    CachedAsyncImage(
                        url: url,
                        content: { image in
                            image.resizable()
                                .aspectRatio(contentMode: .fill)
                                .frame(maxWidth: 130, maxHeight: 170)
                                .clipShape(RoundedRectangle(cornerRadius: 8))
                        },
                        placeholder: {
                            ZStack {
                                Text("Loading..")
                                    .font(.system(size: 16, weight: .medium))
                            }
                            .frame(width: 130, height: 170, alignment: Alignment.center)
                        }
                    )
                }
                VStack(alignment: HorizontalAlignment.leading) {
                    
                    Text(movie.title)
                        .font(.system(size: 18, weight: .bold))
                        .lineLimit(2)
                        .previewDisplayName("Movie Name")
                    
                    Spacer()
                        .frame(height: 4)
                    
                    Text("Rating:\(movie.voteAverage)(\(movie.voteCount))")
                        .font(.system(size: 12, weight: .light))
                        .lineLimit(1)
                        .previewDisplayName("Rating")
                    
                    Spacer()
                        .frame(height: 4)
                    
                    Text(movie.overview)
                        .font(.system(size: 12, weight: .medium))
                        .lineLimit(5)
                        .previewDisplayName("OverView")
                }
                .padding(16)
            }
        }
        .frame(width: .infinity)
        .padding(.init(top: 0, leading: 16, bottom: 24, trailing: 16))
    }
}
