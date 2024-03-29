import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Actives } from './actives.entity';
import { ActivesService } from './actives.service';
import { HttpModule } from '@nestjs/axios';
import { ActivesController } from './actives.controller';
import { MarketService } from './market.service';
import { AnswersModule } from '../answers/answers.module';
import { MarksModule } from '../marks/marks.module';
import { ConfigModule } from '@nestjs/config';

@Module({
  imports: [
    TypeOrmModule.forFeature([Actives]),
    HttpModule,
    AnswersModule,
    MarksModule,
    ConfigModule,
  ],
  providers: [ActivesService, MarketService],
  controllers: [ActivesController],
  exports: [ActivesService, MarketService],
})
export class ActivesModule {}
